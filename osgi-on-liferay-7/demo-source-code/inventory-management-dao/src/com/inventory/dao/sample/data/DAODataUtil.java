package com.inventory.dao.sample.data;

import com.inventory.dao.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DAODataUtil {
	static List<Product> data;
	static int index;
	
	static{
		
		data = new ArrayList<Product>();
		index = 1;
	}
 
	
	public static Product create() {
		
		return new Product();
	}
	
	public static boolean save(Product p) {
		p.setIndex(index++);
		
		try{
			data.add(p);
		}catch(Exception ex) {
			return false;
		}
		
		return true;
		
	}
	
	public static boolean delete(Product p) {
		
		try{
			data.remove(p);
			
		}catch(Exception ex) {
			return false;
		}
		
		return true;
	}
	
	public static boolean update(Product p) {
		
		try{
			delete(p);
			data.add(p);
		}catch(Exception ex) {
			return false;
		}
		
		return true;
		
	}
	public static List<Product> getAll() {
 
		return data;
		
	}

 
}
