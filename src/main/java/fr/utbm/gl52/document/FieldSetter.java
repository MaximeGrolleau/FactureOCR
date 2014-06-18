package fr.utbm.gl52.document;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FieldSetter {
	public static final boolean DISPLAY_ALL_ERRORS = false;
	public static Class<?> getClassOfField(Class<?> thatClass, String fieldName)
	{
		if(thatClass == null || fieldName == null)
		{
			System.out.println("One parameter is null");
			return null;
		}
		try {
			Field field = thatClass.getField(fieldName);
			if(field != null)
				return field.getType();
		} catch (NoSuchFieldException e) {
			if(DISPLAY_ALL_ERRORS) System.out.println("Field " + fieldName + " doesn't exist in " + thatClass.getName());
		} catch (SecurityException e) {
			System.out.println("Security error");
			e.printStackTrace();
		}
		return null;
	}
	public static Class<?> getClassOfLastOfFieldsSeparatedBy(Class<?> thatClass, String fieldsNames, String separator)
	{
		if(thatClass == null || fieldsNames == null)
		{
			System.out.println("One parameter is null");
			return null;
		}
		if(separator == null)
			separator = new String("\\.");
		String[] fields = fieldsNames.split(separator);

		if(fields.length < 1)
			return null;
		
		Class<?> currentClass = thatClass;
		for(int i = 0; i < fields.length; ++i)
		{
			currentClass = getClassOfField(currentClass, fields[i]);
			if(currentClass == null)
				return null;
		}
		return currentClass;
	}
	public static boolean setValueToLastOfFieldsSeparatedBy(Object instanceOfThatClass, Object value, String fieldsNames, String separator)
	{
		if(instanceOfThatClass == null || value == null || fieldsNames == null)
		{
			return false;
		}
		if(separator == null)
			separator = new String("\\.");
		String[] fields = fieldsNames.split(separator);

		if(fields.length < 1)
		{
			System.out.println("Not enough extracted fields");
			return false;
		}
		
		Object currentObject = instanceOfThatClass;
		for(int i = 0; i < fields.length - 1; ++i)
		{
			currentObject = getOrCreateField(currentObject, fields[i]);
			if(currentObject == null)
				return false;
		}
		currentObject = setValueToField(currentObject, value, fields[fields.length - 1]);
		if(currentObject != null)
			return true;
		return false;
	}
	public static Object setValueToField(Object instanceOfThatClass, Object valueToSet, String fieldName)
	{
		if(instanceOfThatClass == null || valueToSet == null || fieldName == null)
		{
			System.out.println("One parameter is null");
			return null;
		}
		Class<?> thatClass = instanceOfThatClass.getClass();
		try {
			Field field = thatClass.getField(fieldName);
			if(field != null)
			{
				try {
					/*if(valueToSet.getClass() != field.getType())
					{
						System.out.println("Different classes : " + valueToSet.getClass().getName() + " and " + field.getType());
					}
					else*/
					{
						field.set(instanceOfThatClass, valueToSet);
						Object newValue = field.get(instanceOfThatClass);
						if(newValue != null)
							return newValue;
						else
							System.out.println("Assignment failed");
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Unable to assign an instance of " + valueToSet.getClass().getName() + " to a field of type " + field.getType());
				} catch (IllegalAccessException e) {
					System.out.println("Argument isn't public in that instance of class " + thatClass.getName());
					e.printStackTrace();
				}
			}
		} catch (NoSuchFieldException e) {
			if(DISPLAY_ALL_ERRORS) System.out.println("Field " + fieldName + " doesn't exist in " + thatClass.getName());
		} catch (SecurityException e) {
			System.out.println("Security error");
			e.printStackTrace();
		}
		return null;
	}
	public static Object getOrCreateField(Object instanceOfThatClass, String fieldName)
	{
		if(instanceOfThatClass == null ||  fieldName == null)
		{
			System.out.println("One parameter is null");
			return null;
		}
		Class<?> thatClass = instanceOfThatClass.getClass();
		try {
			Field field = thatClass.getField(fieldName);
			if(field != null)
			{
				try {
					Object value = field.get(instanceOfThatClass);
					if(value != null)
					{
						return value;
					}
					else
					{
						Object newValue = createInstanceOf(field.getType());
						if(newValue != null)
						{
							field.set(instanceOfThatClass, newValue);
							newValue = field.get(instanceOfThatClass);
							if(newValue != null)
								return newValue;
							else
								System.out.println("Assignment failed");
						}
						else
							System.out.println("Couldn't create an instance of " + field.getType());
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Argument doesn't exist in that instance of class " + thatClass.getName());
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					System.out.println("Argument isn't public in that instance of class " + thatClass.getName());
					e.printStackTrace();
				}
			}
		} catch (NoSuchFieldException e) {
		} catch (SecurityException e) {
			System.out.println("Security error");
			e.printStackTrace();
		}
		return null;
	}
	public static Object createInstanceOf(Class<?> thatClass)
	{
		if(thatClass == null)
		{
			System.out.println("Class is null");
			return null;
		}
		try {
			Constructor<?>[] constructors = thatClass.getConstructors();
			if(constructors.length < 1)
			{
				System.out.println("Class " + thatClass.getName() + " has 0 constructor");
				return null;
			}
			Constructor<?> bestConstructor = null;
			int bestConstructorNbParameters = Integer.MAX_VALUE;
			for(Constructor<?> constructor : constructors)
			{
				if(constructor.getParameterTypes().length < bestConstructorNbParameters)
				{
					bestConstructor = constructor;
					bestConstructorNbParameters = constructor.getParameterTypes().length;
				}
			}
			if(bestConstructor == null)
			{
				System.out.println("Class " + thatClass.getName() + " has no constructor");
				return null;
			}
			try {
				if(bestConstructorNbParameters == 0)
					return bestConstructor.newInstance();
				Object[] params = new Object[bestConstructorNbParameters];
				int i = 0;
				for(Class<?> paramClass : bestConstructor.getParameterTypes())
				{
					params[i] = createInstanceOf(paramClass);
					++i;
				}
				return bestConstructor.newInstance(params);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				System.out.println("Unable to instantiate " + bestConstructor.getName());
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			System.out.println("Unable to get constructors of class " + thatClass.getName());
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
