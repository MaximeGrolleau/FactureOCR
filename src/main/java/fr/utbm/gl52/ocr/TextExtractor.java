package fr.utbm.gl52.ocr;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import fr.utbm.gl52.document.Document;
import fr.utbm.gl52.document.DocumentBuilder;
import fr.utbm.gl52.document.DocumentInfo;
import fr.utbm.gl52.document.DocumentType;
import fr.utbm.gl52.model.Model;
import fr.utbm.gl52.model.Tag;
import fr.utbm.gl52.ocr.net.sourceforge.tess4j.Tesseract;
import fr.utbm.gl52.ocr.net.sourceforge.tess4j.TesseractException;
public class TextExtractor {
	
	private File imageFile;
	private BufferedImage image;
	private Tesseract tesseractInstance;
	
	public TextExtractor(String filePath)
	{
		this.setImageFile(new File(filePath));
		this.setTesseractInstance(Tesseract.getInstance()); // JNA Interface Mapping
	}
	public Document extractToDocument(Model model)
	{
		if(model == null)
			return null;
		try {
			setImage(ImageIO.read(getImageFile()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		Document document = new Document(DocumentType.BILL, imageFile, new DocumentInfo());
		String value;
		
		for(Tag tag : model.getTags())
		{
			double x;
			double y;
			double width;
			double height;

			x = tag.getLocation().getArea().getFromX();
			y = tag.getLocation().getArea().getFromY();
			width = tag.getLocation().getArea().getWidth();
			height = tag.getLocation().getArea().getHeight();
			if(tag.getLocation().getArea().getScale())
			{
				x *= getImage().getWidth();
				y *= getImage().getHeight();
				width *= getImage().getWidth();
				height *= getImage().getHeight();
			}

			value = DocumentBuilder.discardAnyUselessCharacter(extractFromZone(new Rectangle((int)x, (int)y, (int)width, (int)height)));
			if(value != null)
			{
				//System.out.println(value);
				//System.out.println(tag.getLocation().getAfter());
				if(tag.getLocation().getAfter() != null)
					value = extractFromString(tag.getLocation().getAfter(), value);
				if(value != null)
				{
					if(DocumentBuilder.convertAndAddIn(document.getInitialInfos(), tag.getTargetField(), value))
						System.out.println(tag.getTargetField() + " is set to " + value);
					else
						System.out.println(tag.getTargetField() + " wasn't able to be set to " + value);
				}
				else
					System.out.println(tag.getTargetField() + " hasn't enough OCR result");
			}
			else
				System.out.println(tag.getTargetField() + " hasn't any OCR result");
		}
		
		return document;
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
    	int currentPctMatch;
    	String result="";
    	for(int i = 0; i<toSearch.length()-toFind.length(); i++)
    	{
    		currentPctMatch = toFind.length();
    		for(int j=0; j<toFind.length(); j++)
    		{
    			if(!(toSearch.toLowerCase().charAt(i+j)==toFind.toLowerCase().charAt(j)))
    			{
    				currentPctMatch--;
    				//break;
    			}
    		}
    		System.out.println("avant "+currentPctMatch);
			double temp = (currentPctMatch/(double)toFind.length());
			System.out.println("paté "+temp);
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
    			if(!(toSearch.toLowerCase().charAt(i+j)==toFind.toLowerCase().charAt(j)))
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
	    			max = nbChar;
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
    
    public String extractFromString(String toFind, String toSearch, String separator, int pctMatching) //Until line separator found
    {
    	String newline = separator;
    	boolean hasNewLine = toSearch.contains(newline);
    	int currentPctMatch;
    	System.out.println(hasNewLine);
    	String result="";
    	for(int i = 0; i<toSearch.length()-toFind.length(); i++)
    	{
    		currentPctMatch = toFind.length();
    		for(int j=0; j<toFind.length(); j++)
    		{
    			if(!(toSearch.charAt(i+j)==toFind.charAt(j)))
    			{
    				currentPctMatch--;
    			}
    		}
    		double temp = (currentPctMatch/(double)toFind.length());
			currentPctMatch = (int)(temp*100);
			
    		if(currentPctMatch >= pctMatching)
    		{
    			int max;
    			if(!hasNewLine) max = toSearch.length();
    			else {
    				int nbChar = toSearch.indexOf(newline);
	    			max = nbChar;
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
    	
    /*public Set<Field> getArticleFieldFromTable(List<String[]> table){
    	Set<Field> fields = new HashSet<Field>();
    	String[] header = table.get(0);
    	for(int i=1; i<table.size(); i++){
    		List<FieldValue<?>> values = new ArrayList<FieldValue<?>>();
    		for(int j=1; j<header.length; j++){
    			values.add(new FieldValueString(table.get(i)[j]));
    		}
    		fields.add(new Field(table.get(i)[0], values));
    	}
    	return fields;
    }*/
    
    public List<String[]> splitIntoTab(String toSplit, String separator)
   	{
       	String str[] = toSplit.split(separator);
       	ArrayList<String[]> result = new ArrayList<String[]>();
       	for(int i = 0; i<str.length; i++)
       	{
       		result.add(str[i].split(" "));
       	}
   		return result;
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
