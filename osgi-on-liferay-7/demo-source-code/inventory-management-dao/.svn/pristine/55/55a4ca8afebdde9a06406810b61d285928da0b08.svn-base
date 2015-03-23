package com.inventory.dao.impl;

import com.inventory.dao.base.ProductDAO;
import com.inventory.dao.model.Product;
import com.inventory.dao.sample.data.DAODataUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(immediate =true ,
			service = ProductDAO.class
		)
public class ProductDAOImpl implements ProductDAO {

	public Product createProduct() {
		
		return DAODataUtil.create();
	}

	public boolean saveProduct(Product p) {
		try{
			DAODataUtil.save(p);
		}catch(Exception ex) {
			return false;
		}
		return true;
	}

	public boolean updateProduct(Product p) {
		try{
			DAODataUtil.update(p);
		}catch(Exception ex) {
			return false;
		}
		return true;
	}

	public boolean deleteProduct(Product p) {
		try{
			DAODataUtil.delete(p);
		}catch(Exception ex) {
			return false;
		}
		return true;
	}

	public List<Product> getProducts() {
		List<Product> resultList = null;
		try{
			resultList = DAODataUtil.getAll();
		}catch(Exception ex) {
			return null;
		}
		return resultList;
	}

}
