package fr.utbm.gl52.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 
 * @author mgrollea
 * 
 */
@Entity
public class Bill {

	@Id
	@GeneratedValue
	private int billId;

	private int billPriceATI;
	private int billPercentTax;
	private int billPriceET;

	/**
	 * default constructor
	 */
	public Bill(){
		
	}
	
	/**
	 * @param priceATI
	 * @param percentTax
	 * @param priceET
	 */
	public Bill(int priceATI, int percentTax, int priceET) {
		this.setBillPercentTax(percentTax);
		this.billPriceATI = priceATI;
		this.billPriceET = priceET;
	}

	/**
	 * @return the billId
	 */
	public int getBillId() {
		return this.billId;
	}

	/**
	 * @param billId
	 *            the billId to set
	 */
	public void setBillId(int billId) {
		this.billId = billId;
	}

	/**
	 * @return the billPriceATI
	 */
	public int getBillPriceATI() {
		return this.billPriceATI;
	}

	/**
	 * @param billPriceATI
	 *            the billPriceATI to set
	 */
	public void setBillPriceATI(int billPriceATI) {
		this.billPriceATI = billPriceATI;
	}

	/**
	 * @return the billPriceET
	 */
	public int getBillPriceET() {
		return this.billPriceET;
	}

	/**
	 * @param billPriceET
	 *            the billPriceET to set
	 */
	public void setBillPriceET(int billPriceET) {
		this.billPriceET = billPriceET;
	}

	/**
	 * @return the billPercentTax
	 */
	public int getBillPercentTax() {
		return this.billPercentTax;
	}

	/**
	 * @param billPercentTax
	 *            the billPercentTax to set
	 */
	public void setBillPercentTax(int billPercentTax) {
		this.billPercentTax = billPercentTax;
	}

	public String ToString(){
		return new String("Bill [id=" + this.billId + ", taxPercent=" + this.billPercentTax + ", PriceATI=" //$NON-NLS-1$
				+ this.billPriceATI + " Price EC" + this.billPriceET + "]" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
