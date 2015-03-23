package com.inventory.portlet;

import com.inventory.dao.model.Product;
import com.inventory.service.api.ServiceResponse;
import com.inventory.service.base.ProductService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.IOException;
import java.util.Map;

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
import org.springframework.web.portlet.bind.annotation.ActionMapping;
@Component(
		immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Inventory View Panel",
            	"com.liferay.portlet.private-request-attributes=false",
        		"com.liferay.portlet.private-session-attributes=false",
                "javax.portlet.portlet-mode=text/html;edit,view",
                "javax.portlet.init-param.template-path=/",
        		"javax.portlet.init-param.edit-template=/edit.jsp",
        		"com.liferay.portlet.icon=/icon.png",
        		"javax.portlet.init-param.view-template=/view.jsp",     		
        		"javax.portlet.expiration-cache=0",
        		"javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class InventoryViewPortlet extends MVCPortlet {
	
	private ProductService productService;
	 
	 @Reference
	 public void setProductService(ProductService productService) {
		 
	        this.productService = productService;
	 }
	 

	 
	@Override
   public void doView(RenderRequest request, RenderResponse response)
       throws PortletException, IOException {
		
		init(request,response);
		
		  ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		   boolean signedIn = themeDisplay.isSignedIn();   
		   request.setAttribute("signedIn", signedIn);
		   
       super.doView(request, response);
        
   }
	
	private void init(RenderRequest request, RenderResponse response) {
		ServiceResponse service = null;
		Map<String,Object> response_  = productService.getProducts(request);
		
		if(response_!=null) {
			service = (ServiceResponse) response_.get("response");
			if(service.isSuccess()){
				request.setAttribute("products", response_.get("products"));
			}else{
				request.setAttribute("products",null);
			}
		}
		
	}



	@ActionMapping(value="deleteProduct")
	public void deleteProduct(ActionRequest request, ActionResponse response) throws IOException {
		
		int index = ParamUtil.getInteger(request, "index");
		ServiceResponse service = productService.createProduct(request);
		
		if(service.isSuccess()) {
			Product p = service.getProduct();
			p.setIndex(index);					
			service = productService.deleteProduct(p,request);
			
			if(!service.isSuccess()){
				returnError(request, response, service);	
			}else{
				
				returnSuccess(request, response, service);
			}
		
		}else{
			returnError(request, response, service);	
		}
		
	}
	

	@ProcessAction(name = "updateProduct")
	public void updateProduct(ActionRequest request, ActionResponse response) throws IOException {
		
		String description = ParamUtil.getString(request, "description");
		double cost =  ParamUtil.getDouble(request, "cost");
		double  price =  ParamUtil.getDouble(request, "price");
		int qty =  ParamUtil.getInteger(request, "qty");
		int index =  ParamUtil.getInteger(request, "index");
		
		ServiceResponse service = productService.createProduct(request);
		if(service.isSuccess()) {
			
			Product p = service.getProduct();
			p.setDescription(description);
			p.setCostValue(cost);
			p.setPriceValue(price);
			p.setStockValue(qty);
			p.setIndex(index);
	
			service  = productService.updateProduct(p,request);
			
			if(!service.isSuccess()) {
				returnError(request, response, service);	
			}else{	
				returnSuccess(request, response, service);
			}
		
		}else{
			returnError(request, response, service);	
		}
		

	  
	  }
	
	private void returnError(PortletRequest req,ActionResponse res, ServiceResponse service) throws IOException {
		SessionErrors.add(req, "");
		req.setAttribute("msg", service.getMessage());
		//res.setRenderParameter("jspPage","/view.jsp");
		//res.setProperty("msg", service.getMessage());
		SessionErrors.add(req, "error");
		res.sendRedirect("/web/guest");
		
	}
	
	private void returnSuccess(PortletRequest req,ActionResponse res, ServiceResponse service) throws IOException {
		//res.setRenderParameter("jspPage","/view.jsp");
		res.sendRedirect("/web/guest");
	}
}
