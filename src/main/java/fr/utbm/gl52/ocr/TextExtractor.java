package fr.utbm.gl52.ocr;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ocr.net.sourceforge.tess4j.Tesseract;
import ocr.net.sourceforge.tess4j.TesseractException;
import fr.utbm.gl52.model.Models;

public class TextExtractor {
	
	private File imageFile;
	private BufferedImage image;
	private Tesseract tesseractInstance;
	
	public TextExtractor(String filePath)
	{
		this.setImageFile(new File(filePath));
		this.setTesseractInstance(Tesseract.getInstance()); // JNA Interface Mapping
		
	}
    public static void main(String[] args) {
    	TextExtractor te = new TextExtractor("FactureOCR_0001.jpg");
        
        // Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
    	
    	String mouche = "La brochette de yannis avait un gros p�doncule goulument assorti d'une mayonnaise divine. \nIl s'en saisit et marcha une centaine de km.";
    	String result = te.extractFromString("bruchotte", mouche, 12, 60);
    	System.out.println(result);
    	
    	Models models = new Models();
    	models.exemple();
    	try {
			te.setImage(ImageIO.read(te.getImageFile()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	

		/*Model model = models.getModels().get(0);
		for(ModelField field : model.getFields())
		{
			field.getField().getValues().get(0).setTextValue(
					te.extractFromString(field.getLocation().getAfter(), 
							te.extractFromZone(new Rectangle(
									(int)(field.getLocation().getArea().getFromX() * te.getImage().getWidth()),
									(int)(field.getLocation().getArea().getFromY() * te.getImage().getHeight()),
									(int)(field.getLocation().getArea().getWidth() * te.getImage().getWidth()),
									(int)(field.getLocation().getArea().getHeight() * te.getImage().getHeight())

									))
							)
					);
System.out.println(field.getLocation().getAfter());
System.out.println(te.extractFromZone(new Rectangle(
		(int)(field.getLocation().getArea().getFromX() * te.getImage().getWidth()),
		(int)(field.getLocation().getArea().getFromY() * te.getImage().getHeight()),
		(int)(field.getLocation().getArea().getWidth() * te.getImage().getWidth()),
		(int)(field.getLocation().getArea().getHeight() * te.getImage().getHeight()))));
			field.getField().getValues().get(0).extract();
		}
		for(ModelField field : model.getFields())
		{
			for(FieldValue<?> value : field.getField().getValues())
				value.extract();
		}
		for(ModelField field : model.getFields())
		{
			for(FieldValue<?> value : field.getField().getValues())
				System.out.println(field.getField().getName() + " : " + value.getExtractedValue());
		}*/
		
        /*try {
        	Point pos = new Point(0, 0);
        	int width = 1000;
        	int height = 400;
        	Rectangle rect = new Rectangle(pos.x,pos.y,width,height);
            String result = te.getTesseractInstance().doOCR(te.getImageFile(), rect);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }*/
    }
    
    public String extractFromZone(Rectangle rect)
    {
    	try{
    		String result = this.tesseractInstance.doOCR(this.imageFile, rect);
    		return result;
    	} catch(TesseractException e) {
    		System.err.println(e.getMessage());
    	}
    	return null;
    }
    
    public String extractFromString(String toFind, String toSearch, int nbChar){
    	boolean allWordFind;
    	String result="";
    	for(int i = 0; i<toSearch.length()-toFind.length(); i++)
    	{
    		allWordFind = true;
    		for(int j=0; j<toFind.length(); j++)
    		{
    			if(!(toSearch.toLowerCase().charAt(i+j)==toFind.toLowerCase().charAt(j)))
    			{
    				allWordFind = false;
    				break;
    			}
    		}
    		if(allWordFind == true)
    		{
    			int max = nbChar+i+toFind.length();
    			if(max > toSearch.length())
    				max = toSearch.length();
    			for(int j=i+toFind.length(); j<max; j++)
    				result+=toSearch.charAt(j);
    			return result;
    		}
    	}
    	return null;
    }
    
    public String extractFromString(String toFind, String toSearch, int nbChar, int pctMatching){
    	boolean allWordFind;
    	int currentPctMatch;
    	String result="";
    	for(int i = 0; i<toSearch.length()-toFind.length(); i++)
    	{
    		allWordFind = true;
    		currentPctMatch = toFind.length();
    		for(int j=0; j<toFind.length(); j++)
    		{
    			if(!(toSearch.toLowerCase().charAt(i+j)==toFind.toLowerCase().charAt(j)))
    			{
    				allWordFind = false;
    				currentPctMatch--;
    				//break;
    			}
    		}
    		System.out.println("avant "+currentPctMatch);
			double temp = (currentPctMatch/(double)toFind.length());
			System.out.println("pat� "+temp);
			currentPctMatch = (int)(temp*100);
			System.out.println("d'mouche "+currentPctMatch);
			
    		if(currentPctMatch >= pctMatching)
    		{
    			int max = nbChar+i+toFind.length();
    			if(max > toSearch.length())
    				max = toSearch.length();
    			for(int j=i+toFind.length(); j<max; j++)
    				result+=toSearch.charAt(j);
    			return result;
    		}
    	}
    	return null;
    }
    
    public String extractFromString(String toFind, String toSearch) //Until line separator found
    {
    	String newline = "\n";
    	boolean hasNewLine = toSearch.contains(newline);
    	System.out.println(hasNewLine);
    	boolean allWordFind;
    	String result="";
    	for(int i = 0; i<toSearch.length()-toFind.length(); i++)
    	{
    		allWordFind = true;
    		for(int j=0; j<toFind.length(); j++)
    		{
    			if(!(toSearch.charAt(i+j)==toFind.charAt(j)))
    			{
    				allWordFind = false;
    				break;
    			}
    		}
    		if(allWordFind == true)
    		{
    			int max;
    			if(!hasNewLine) max = toSearch.length();
    			else {
    				int nbChar = toSearch.indexOf(newline);
	    			max = nbChar+i+toFind.length();
	    			if(max > toSearch.length())
	    				max = toSearch.length();
    			}
    			for(int j=i+toFind.length(); j<max; j++)
    				result+=toSearch.charAt(j);
    			return result;
    		}
    	}
    	return null;
    }
    	
    
	public Tesseract getTesseractInstance() {
		return tesseractInstance;
	}
	public void setTesseractInstance(Tesseract tesseractInstance) {
		this.tesseractInstance = tesseractInstance;
	}
	public File getImageFile() {
		return imageFile;
	}
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
