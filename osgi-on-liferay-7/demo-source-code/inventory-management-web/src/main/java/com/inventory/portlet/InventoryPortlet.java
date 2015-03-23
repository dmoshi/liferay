package com.inventory.portlet;

import com.inventory.dao.model.Product;
import com.inventory.service.api.ServiceResponse;
import com.inventory.service.base.ProductService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
 

@Component(
		immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Inventory Management Panel",
            	"com.liferay.portlet.private-request-attributes=false",
        		"com.liferay.portlet.private-session-attributes=false",
                "javax.portlet.portlet-mode=text/html;edit,view",
                "javax.portlet.init-param.template-path=/",
        		"javax.portlet.init-param.edit-template=/edit.jsp",
        		"com.liferay.portlet.icon=/icon.png",
        		"javax.portlet.init-param.view-template=/view.jsp",     		
        		"javax.portlet.expiration-cache=0",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class InventoryPortlet  extends MVCPortlet {
	
	private ProductService productService;
	 
	 @Reference
	 public void setProductService(ProductService productService) {
		 
	        this.productService = productService;
	 }
	 
 
	 
	@Override
    public void doView(RenderRequest request, RenderResponse response)
        throws PortletException, IOException {
		
        super.doView(request, response);
        
        
         
    }

	@ProcessAction(name = "addProduct")
	public void addProduct(ActionRequest request, ActionResponse response) throws IOException {
		
		String description = ParamUtil.getString(request, "description");
		double cost =  ParamUtil.getDouble(request, "cost");
		double  price =  ParamUtil.getDouble(request, "price");
		int qty =  ParamUtil.getInteger(request, "qty");
		
		ServiceResponse service = productService.createProduct(request);
		
		if(service.isSuccess()) {
			Product p = service.getProduct();
			p.setDescription(description);
			p.setCostValue(cost);
			p.setPriceValue(price);
			p.setStockValue(qty);
			service = productService.saveProduct(p,request);
			if(!service.isSuccess())
				returnError(request, response, service);			
		}else{
			returnError(request, response, service);
		}
	 	
	}
	
	private void returnError(PortletRequest req,ActionResponse res, ServiceResponse service) throws IOException {
		SessionErrors.add(req, "");
		req.setAttribute("msg_", service.getMessage());
		res.setRenderParameter("jspPage","/view.jsp");
	}
	


}
