package com.inventory.dao.model;

public class  Product {

	private String description;
	private int stockValue;
	private double costValue;
	private double priceValue;
	private int index;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStockValue() {
		return stockValue;
	}
	public void setStockValue(int stockValue) {
		this.stockValue = stockValue;
	}
	public double getCostValue() {
		return costValue;
	}
	public void setCostValue(double costValue) {
		this.costValue = costValue;
	}
	public double getPriceValue() {
		return priceValue;
	}
	public void setPriceValue(double priceValue) {
		this.priceValue = priceValue;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	//Products are equual based on index field
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (index != other.index)
			return false;
		return true;
	}
	
	
	

}
