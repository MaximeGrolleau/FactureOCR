package fr.utbm.gl52.document;

public class Price {

	private float priceIncludingTaxes;
	private float priceExcludingTaxes;
	private Currency currency = Currency.DEFLT;
	
	public Price(float amountWithTaxes, float amountWithoutTaxes, Currency currency){
		this.setCurrency(currency);
		this.setPriceExcludingTaxes(amountWithoutTaxes);
		this.setPriceIncludingTaxes(amountWithTaxes);
	}

	public Price() {}

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
