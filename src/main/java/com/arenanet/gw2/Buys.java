package com.arenanet.gw2;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Buys{

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("unit_price")
	private int unitPrice;

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setUnitPrice(int unitPrice){
		this.unitPrice = unitPrice;
	}

	public int getUnitPrice(){
		return unitPrice;
	}

	@Override
 	public String toString(){
		return 
			"Buys{" + 
			"quantity = '" + quantity + '\'' + 
			",unit_price = '" + unitPrice + '\'' + 
			"}";
		}
}