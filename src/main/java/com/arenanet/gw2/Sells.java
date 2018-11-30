package com.arenanet.gw2;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The type Sells.
 */
@Generated("com.robohorse.robopojogenerator")
public class Sells{

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("unit_price")
	private int unitPrice;

	/**
	 * Set quantity.
	 *
	 * @param quantity the quantity
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	/**
	 * Get quantity int.
	 *
	 * @return the int
	 */
	public int getQuantity(){
		return quantity;
	}

	/**
	 * Set unit price.
	 *
	 * @param unitPrice the unit price
	 */
	public void setUnitPrice(int unitPrice){
		this.unitPrice = unitPrice;
	}

	/**
	 * Get unit price int.
	 *
	 * @return the int
	 */
	public int getUnitPrice(){
		return unitPrice;
	}

	@Override
 	public String toString(){
		return 
			"Sells{" + 
			"quantity = '" + quantity + '\'' + 
			",unit_price = '" + unitPrice + '\'' + 
			"}";
		}
}