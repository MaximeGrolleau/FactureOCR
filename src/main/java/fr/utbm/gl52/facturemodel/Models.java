package fr.utbm.gl52.facturemodel;

import java.util.ArrayList;
import java.util.List;

public class Models {
	private List<Model> models;
	public Models() {
		models = new ArrayList<Model>();
	}
	public void exemple()
	{
		Model model = new Model(Model.DOCUMENT_TYPE.BILL, "exemple");
		model.addField(new ModelField(new Location(new ImageArea(null, 0.0, 0.0, 0.5, 0.5), "sommes"), new FieldValueString()));
		model.addField(new ModelField(new Location(new ImageArea(null, 0.0, 0.5, 0.5, 1.0), "Facture"), new FieldValueString()));
		model.addField(new ModelField(new Location(new ImageArea(null, 0.5, 0.0, 1.0, 0.5), "SHOE"), new FieldValueString()));
		model.addField(new ModelField(new Location(new ImageArea(null, 0.5, 0.5, 1.0, 1.0), "Total"), new FieldValueString()));
		models.add(model);
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

}