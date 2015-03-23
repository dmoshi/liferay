package com.inventory.service.impl;

import com.inventory.dao.base.ProductDAO;
import com.inventory.dao.model.Product;
import com.inventory.security.api.AuthMessages;
import com.inventory.security.api.AuthResponse;
import com.inventory.security.api.AuthType;
import com.inventory.security.base.Authentication;
import com.inventory.service.api.ResponseMessages;
import com.inventory.service.api.ServiceResponse;
import com.inventory.service.base.ProductService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate=true,
		service=ProductService.class
		)
public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;
	private Authentication authenticate;
	private ServiceResponse response = new ServiceResponse();
 

	public ServiceResponse createProduct(PortletRequest request) {
		
		try {
			AuthResponse auth = authenticate.authenticate(request, AuthType.PRODUCT_MANAGER);
			
			if(auth.isValid()) {
				Product p = productDAO.createProduct();
				response.setProduct(p);
				response.setSuccess(auth.isValid());
			}else{
				response.setSuccess(auth.isValid());
				response.setMessage(AuthMessages.ROLE_ERROR);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		

	 	
		return response;
	}
 
	public ServiceResponse saveProduct(Product p,PortletRequest request) {
		
		try {
			AuthResponse auth = authenticate.authenticate(request, AuthType.PRODUCT_MANAGER);
			if(auth.isValid()) {
				boolean result = productDAO.saveProduct(p);	
				response.setSuccess(result);	
				if(!result)
					response.setMessage(ResponseMessages.DAO_SAVE_ERROR);
				response.setProduct(p);
			}else{
				response.setSuccess(auth.isValid());
				response.setMessage(AuthMessages.ROLE_ERROR);
			}
		} catch (PortalException e) {
 
			e.printStackTrace();
		}
		
		
		
		return response;
	}

	public ServiceResponse updateProduct(Product p,PortletRequest request) {
		
		try {
			AuthResponse auth = authenticate.authenticate(request, AuthType.PRODUCT_MANAGER);
			if(auth.isValid()){
				boolean result = productDAO.updateProduct(p);
				if(!result)
					response.setMessage(ResponseMessages.DAO_UPDATE_ERROR);
				response.setSuccess(result);
				response.setProduct(p);
			}else{
				response.setSuccess(auth.isValid());
				response.setMessage(AuthMessages.ROLE_ERROR);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
	
		return response;
	}

	public ServiceResponse deleteProduct(Product p,PortletRequest request) {
		
		try {
			AuthResponse auth = authenticate.authenticate(request,  AuthType.PRODUCT_MANAGER);
			if(auth.isValid()){
				boolean result = productDAO.deleteProduct(p);
				if(!result)
					response.setMessage(ResponseMessages.DAO_DEL_ERROR);
				response.setSuccess(result);
				response.setProduct(p);
			}else{
				response.setSuccess(auth.isValid());
				response.setMessage(AuthMessages.ROLE_ERROR);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		

		return response;
	}

	public Map<String,Object> getProducts(PortletRequest request) {
		Map<String,Object> result = null ;
		try {
			AuthResponse auth = authenticate.authenticate(request, AuthType.PRODUCT_MANAGER);
			
			//Forcing products view available to all users, can be removed if necessary
			auth.setValid(true);
			
			if(auth.isValid()){
				result = new HashMap<String, Object>();		
				response.setSuccess(true);
				result.put("response", response);
				result.put("products", productDAO.getProducts());
			} 
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return result ;
	}
	
	@Reference
	public void setAuthenticate(Authentication authenticate) {
		this.authenticate = authenticate;
	}
	
	@Reference
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	

	
	

}
