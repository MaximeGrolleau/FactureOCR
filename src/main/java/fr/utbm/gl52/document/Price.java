package fr.utbm.gl52.document;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Price {
	@GeneratedValue
	@Id
	private int id;
	
	private float priceIncludingTaxes;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	private float priceExcludingTaxes;
	
	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	public Price(float amountWithTaxes, float amountWithoutTaxes, Currency currency){
		this.setCurrency(currency);
		this.setPriceExcludingTaxes(amountWithoutTaxes);
		this.setPriceIncludingTaxes(amountWithTaxes);
	}

	public float getPriceIncludingTaxes() {
		return priceIncludingTaxes;
	}

	public void setPriceIncludingTaxes(float priceIncludingTaxes) {
		this.priceIncludingTaxes = priceIncludingTaxes;
	}

	public float getPriceExcludingTaxes() {
		return priceExcludingTaxes;
	}

	public void setPriceExcludingTaxes(float priceExcludingTaxes) {
		this.priceExcludingTaxes = priceExcludingTaxes;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
}
