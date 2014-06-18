package fr.utbm.gl52.document;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;


public class DocumentBuilder {

	public static boolean convertAndAddIn(Object instance, String fieldsNames, String value)
	{
		if(instance == null || value == null)
		{
			System.out.println("One parameter is null");
			return false;
		}
		return FieldSetter.setValueToLastOfFieldsSeparatedBy(instance, value, fieldsNames, null);
		
	}
	/*public static boolean convertAndAddIn(Object instance, String fieldsNames, String value)
	{
		if(instance == null || value == null)
		{
			System.out.println("One parameter is null");
			return false;
		}
		Class<?> thatClass = FieldSetter.getClassOfLastOfFieldsSeparatedBy(instance.getClass(), fieldsNames, null);
		if(thatClass != null)
		{
			System.out.println(thatClass);
			NumberFormat numberFormater = NumberFormat.getInstance();
			Object convertedValue = null;
			String cleanValue = discardAnyNonNumericCharacter(discardAnyUselessCharacter(discardAnyLineAfterTheFirst(value)));
			if(((DecimalFormat)DecimalFormat.getInstance()).getDecimalFormatSymbols().getDecimalSeparator() == ',')
				cleanValue = cleanValue.replace('.', ',');
			try {
				if(thatClass == float.class || thatClass == Float.class)
					convertedValue = numberFormater.parse(cleanValue).floatValue();
				else if(thatClass == double.class || thatClass == Double.class)
					convertedValue = numberFormater.parse(cleanValue).doubleValue();
				else if(thatClass == int.class || thatClass == Integer.class)
					convertedValue = numberFormater.parse(cleanValue).intValue();
				else if(thatClass == String.class)
					convertedValue = discardAnyUselessCharacter(value);
				else
				{
					System.out.println("Unable to assign to a field an instance of unhandled type " + thatClass.getName());
					return false;
				}
				return FieldSetter.setValueToLastOfFieldsSeparatedBy(instance, convertedValue, fieldsNames, null);
			} catch (ParseException e) {
				convertedValue = 0.0;
			}
		}
		return false;
	}*/
	public static int getIntFromString(String string)
	{
		NumberFormat numberFormater = NumberFormat.getInstance();
		String cleanValue = discardAnyNonNumericCharacter(discardAnyUselessCharacter(discardAnyLineAfterTheFirst(string)));
		try {
			return numberFormater.parse(cleanValue).intValue();
		} catch (ParseException e) {
		}
		return 0;
	}
	public static double getDoubleFromString(String string)
	{
		NumberFormat numberFormater = NumberFormat.getInstance();
		String cleanValue = discardAnyNonNumericCharacter(discardAnyUselessCharacter(discardAnyLineAfterTheFirst(string)));
		if(((DecimalFormat)DecimalFormat.getInstance()).getDecimalFormatSymbols().getDecimalSeparator() == ',')
			cleanValue = cleanValue.replace('.', ',');
		try {
			return numberFormater.parse(cleanValue).doubleValue();
		} catch (ParseException e) {
		}
		return 0;
	}
	public static float getFloatFromString(String string)
	{
		NumberFormat numberFormater = NumberFormat.getInstance();
		String cleanValue = discardAnyNonNumericCharacter(discardAnyUselessCharacter(discardAnyLineAfterTheFirst(string)));
		if(((DecimalFormat)DecimalFormat.getInstance()).getDecimalFormatSymbols().getDecimalSeparator() == ',')
			cleanValue = cleanValue.replace('.', ',');
		try {
			return numberFormater.parse(cleanValue).floatValue();
		} catch (ParseException e) {
		}
		return 0;
	}
	public static String discardAnyNonNumericCharacter(String string)
	{
		String result = new String();
		for(int i = 0; i < string.length(); ++i)
		{
			if((string.charAt(i) >= '0' && string.charAt(i) <= '9') || string.charAt(i) == ',' || string.charAt(i) == '-' || string.charAt(i) == '.')
				result += new String("" + string.charAt(i));
		}
		return result;
	}
	public static String discardAnyUselessCharacter(String string)
	{
		String result = new String();
		
		int i;
		for(i = 0; i < string.length(); ++i)
		{
			if(string.charAt(i) != ' ' && string.charAt(i) != '\n')
				break;
		}
		
		int stopAt;
		for(stopAt = string.length() - 1; stopAt >= 0; --stopAt)
		{
			if(string.charAt(stopAt) != ' ' && string.charAt(stopAt) != '\n')
				break;
		}

		for(int j = i; j <= stopAt; ++j)
			result += string.charAt(j);
		return result;
	}
	public static String discardAnyLineAfterTheFirst(String string)
	{
		String result = new String();
		int i;
		for(i = 0; i < string.length(); ++i)
		{
			if(string.charAt(i) == '\n')
				break;
			else
				result += string.charAt(i);
		}
		return result;
	}
}
