package com.arenanet.gw2;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Results {

	@JsonProperty("item_id")
	private int itemId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("id")
	private int id;

	@JsonProperty("order")
	private int order;

	public Results() {}

	public Results(int id, String name, String icon, int order, int itemId) {
		this.name = name;
		this.icon = icon;
		this.id = id;
		this.order = order;
	}

	public void setItemId(int itemId){
		this.itemId = itemId;
	}

	public int getItemId(){
		return itemId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setOrder(int order){
		this.order = order;
	}

	public int getOrder(){
		return order;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Results results = (Results) o;

		if (itemId != results.itemId) return false;
		if (id != results.id) return false;
		if (order != results.order) return false;
		if (name != null ? !name.equals(results.name) : results.name != null) return false;
		if (icon != null ? !icon.equals(results.icon) : results.icon != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = itemId;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (icon != null ? icon.hashCode() : 0);
		result = 31 * result + id;
		result = 31 * result + order;
		return result;
	}

	@Override
 	public String toString(){
		return 
			"Results{" + 
			"item_id = '" + itemId + '\'' + 
			",name = '" + name + '\'' + 
			",icon = '" + icon + '\'' + 
			",id = '" + id + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}