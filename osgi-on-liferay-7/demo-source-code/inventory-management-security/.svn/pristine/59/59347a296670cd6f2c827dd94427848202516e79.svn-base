package com.inventory.security.impl;

import com.inventory.security.api.AuthMessages;
import com.inventory.security.api.AuthResponse;
import com.inventory.security.base.Authentication;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.util.PortalUtil;

import java.util.List;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate=true,
	service=Authentication.class
)
public class ProductAuthentication implements Authentication {
	
	private UserLocalService userLocalService;


 
	public AuthResponse authenticate(PortletRequest request,String authType) throws PortalException {
		AuthResponse res =  new AuthResponse();
		long userId  = PortalUtil.getUserId(request);	
		User user  = userLocalService.getUserById(userId);
		if(user!=null) {
				List<Role> roles = user.getRoles();
				boolean flag = false;
				for(Role r: roles) {
					if(r.getName().equals(authType)){
						flag = true;
						break;
					}
				}
			
		
			if(!flag){
				res.setMessage(AuthMessages.ROLE_ERROR);
			}
				
				res.setValid(flag);
				res.setAuthType(authType);
		}else{
			res.setValid(false);
			res.setAuthType(authType);
		}
		
		return res;
	}
	
	
	@Reference
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService =  userLocalService;
	}
	

}
