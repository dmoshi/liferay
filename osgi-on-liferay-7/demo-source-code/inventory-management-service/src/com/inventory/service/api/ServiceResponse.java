package com.inventory.service.api;

import com.inventory.dao.model.Product;

public class ServiceResponse {
	
	private Product product;
	private String message;
	private boolean success;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	

}
