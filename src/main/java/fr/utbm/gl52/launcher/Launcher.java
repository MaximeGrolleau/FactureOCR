package fr.utbm.gl52.launcher;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import fr.utbm.gl52.database.HibernateUtil;
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

		HibernateUtil.getSession();
		
		List<Model> models = new ArrayList<Model>();
		
		File[] modelReceipt = new File("models//receipt").listFiles();
		models.addAll(loadModels(modelReceipt, DocumentType.RECEIPT));

		File[] modelBill = new File("models//bill").listFiles();
		models.addAll(loadModels(modelBill, DocumentType.BILL));
		
		TextExtractor te = new TextExtractor();
		AppFrame mainFrame = new AppFrame(te, models);
		
		HibernateUtil.shutdown();
	}
	
	/**
	 * @param modelFiles
	 * @param type
	 * @return
	 */
	public static List<Model> loadModels(File[] modelFiles, DocumentType type){
		
		List<Model> models = new ArrayList<Model>();
		
		for(int j=0; j<modelFiles.length; j++)
		{
			if(!modelFiles[j].isDirectory())
			{
				Model newModel = new Model(type);
				newModel.setName(modelFiles[j].getName());
				List<String> content = readModel(modelFiles[j]);
				if (!content.isEmpty()){
					for(int i=1; i<content.size(); i++){
						content.set(i, content.get(i).replace("\n", " "));
						content.set(i, content.get(i).trim());
						String[] contentLine = content.get(i).split(";");
						if(contentLine.length == 4)
						{
							String[] targetFields = contentLine[0].split("\\.");
							if(targetFields.length > 0)
							{
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
	
								Tag tag = new Tag(contentLine[0], new Location(imageArea, contentLine[1]));
								if(targetFields[0].equalsIgnoreCase("products"))
								{
									if(targetFields.length == 1)
									{
										newModel.setProductsArea(tag);
									}
									else
									{
										String targetFieldsMinusFirst = new String(targetFields[1]);
										for(int k = 2; k < targetFields.length; ++k)
											targetFieldsMinusFirst += "." + targetFields[k];
										tag.setTargetField(targetFieldsMinusFirst);
										//tag.getLocation().getArea().setRelative(newModel.getProductsArea().getLocation().getArea());
										newModel.addProductTag(tag);
									}
								}
								else
								{
									newModel.addTag(tag);
								}
							}
							else
								System.out.println("Wrong model format : empty target field");
						}
						else
							System.out.println("Wrong model format : too many semicolons by line");
					}
				}

				models.add(newModel);
			}
		}
		return models;
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
