package fr.utbm.gl52.launcher;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import fr.utbm.gl52.document.Document;
import fr.utbm.gl52.document.DocumentBuilder;
import fr.utbm.gl52.document.DocumentType;
import fr.utbm.gl52.gui.AppFrame;
import fr.utbm.gl52.model.ImageArea;
import fr.utbm.gl52.model.Location;
import fr.utbm.gl52.model.Model;
import fr.utbm.gl52.model.Tag;
import fr.utbm.gl52.ocr.TextExtractor;

/**
 * @author mgrollea
 *
 */
public class Launcher {

	/**
	 * @param argv
	 */
	public static void main(String[] argv) {
		
		File modelFile = new File("models/modeltest.csv");
		Model newModel = new Model(DocumentType.RECEIPT);
		List<String> content = readModel(modelFile);
		
		if (!content.isEmpty()){
			for(int i=1; i<content.size(); i++){
				content.set(i, content.get(i).replace("\n", " "));
				content.set(i, content.get(i).trim());
				String[] contentLine = content.get(i).split(";");
				
				String upLeftCorner = contentLine[2].substring(1, contentLine[2].length()-1);
				upLeftCorner = upLeftCorner.trim();
				String[] upCorner = upLeftCorner.split(",");

				String bottomRightCorner = contentLine[3].replace('(', ' ');
				bottomRightCorner = bottomRightCorner.trim();
				String[] btmCorner = bottomRightCorner.split(",");

				ImageArea imageArea = new ImageArea(null,
						Double.parseDouble(DocumentBuilder.discardAnyNonNumericCharacter(upCorner[0])),
						Double.parseDouble(DocumentBuilder.discardAnyNonNumericCharacter(upCorner[1])),
						Double.parseDouble(DocumentBuilder.discardAnyNonNumericCharacter(btmCorner[0])),
						Double.parseDouble(DocumentBuilder.discardAnyNonNumericCharacter(btmCorner[1])),
						false);
				if(Double.parseDouble(upCorner[0]) + Double.parseDouble(upCorner[1]) <= 2.0)
					imageArea.setScale(true);
				newModel.addTag(new Tag(contentLine[0], new Location(imageArea, contentLine[1])));
			}
 		}
		
		TextExtractor te = new TextExtractor("ImageTest/FactureOCR_0001.jpg");
		Document document = te.extractToDocument(newModel);
		
		AppFrame mainFrame = new AppFrame();
	}
	 public static List<String> readModel(File f) {
		 List<String> content = new ArrayList<String>();
		    try {
		       BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
		       int b;
		       StringWriter out = new StringWriter();
		       while ((b=in.read()) != -1){
		    	   if(b != 13){
		    		   out.write(b);
		    	   } else {
		    		   content.add(out.toString());
			    	   out = new StringWriter();
		    	   }
		       }
		       out.close();
		       in.close();
		    }
		    catch (IOException ie)
		    {
		         ie.printStackTrace(); 
		    }
		    
		    return content;
	}
}
