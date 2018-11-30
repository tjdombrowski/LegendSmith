package com.arenanet.gw2;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The type Response.
 */
@Generated("com.robohorse.robopojogenerator")
public class Response{

	@JsonProperty("buys")
	private Buys buys;

	@JsonProperty("sells")
	private Sells sells;

	@JsonProperty("id")
	private int id;

	@JsonProperty("whitelisted")
	private boolean whitelisted;

	/**
	 * Set buys.
	 *
	 * @param buys the buys
	 */
	public void setBuys(Buys buys){
		this.buys = buys;
	}

	/**
	 * Get buys buys.
	 *
	 * @return the buys
	 */
	public Buys getBuys(){
		return buys;
	}

	/**
	 * Set sells.
	 *
	 * @param sells the sells
	 */
	public void setSells(Sells sells){
		this.sells = sells;
	}

	/**
	 * Get sells sells.
	 *
	 * @return the sells
	 */
	public Sells getSells(){
		return sells;
	}

	/**
	 * Set id.
	 *
	 * @param id the id
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Get id int.
	 *
	 * @return the int
	 */
	public int getId(){
		return id;
	}

	/**
	 * Set whitelisted.
	 *
	 * @param whitelisted the whitelisted
	 */
	public void setWhitelisted(boolean whitelisted){
		this.whitelisted = whitelisted;
	}

	/**
	 * Is whitelisted boolean.
	 *
	 * @return the boolean
	 */
	public boolean isWhitelisted(){
		return whitelisted;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"buys = '" + buys + '\'' + 
			",sells = '" + sells + '\'' + 
			",id = '" + id + '\'' + 
			",whitelisted = '" + whitelisted + '\'' + 
			"}";
		}
}