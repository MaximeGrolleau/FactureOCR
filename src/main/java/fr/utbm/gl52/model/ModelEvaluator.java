package fr.utbm.gl52.model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import fr.utbm.gl52.ocr.TextExtractor;

public class ModelEvaluator {
	public static Model evaluate(File file, List<Model> possibleModels)
	{
		if(possibleModels.size() == 0)
			return null;
		if(possibleModels.size() == 1)
			return possibleModels.get(0);
		
		TextExtractor te = new TextExtractor(file);
		
    	try {
			te.setImage(ImageIO.read(te.getImageFile()));
		} catch (IOException e) {
			System.out.println("Unable to load image for model estimation : ");
			e.printStackTrace();
			return null;
		}
    	
    	
		
		return possibleModels.get(0);
	}
}
