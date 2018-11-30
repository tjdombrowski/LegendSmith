package com.arenanet.gw2;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

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

	public void setBuys(Buys buys){
		this.buys = buys;
	}

	public Buys getBuys(){
		return buys;
	}

	public void setSells(Sells sells){
		this.sells = sells;
	}

	public Sells getSells(){
		return sells;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setWhitelisted(boolean whitelisted){
		this.whitelisted = whitelisted;
	}

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