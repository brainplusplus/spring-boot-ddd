package com.app.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.app.entity.PageApp;
import com.app.entity.Role;
import com.app.entity.RolePage;
import com.app.service.AppContextService;
import com.app.service.master.RolePageService;
import com.app.service.master.RoleService;

@Service
public class AuthChecking {
	
	@Autowired
	HttpServletRequest request;

	@Autowired
	private AppContextService appCtx;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RolePageService rolePageService;	
	
	public boolean isAuthenticatedMenu(String uri) {
   	 	boolean isDenied = true;
		System.out.println("--isAuthenticatedMenu-- :"+uri);
		System.out.println("--Name-- : "+appCtx.getUsername());
   	 	System.out.println("request:"+request);
		appCtx.setAuthBySession(request.getSession());
		Authentication auth = appCtx.getAuth();
		if(auth!=null){
			System.out.println("--Auth-- : "+auth.isAuthenticated());
			System.out.println("--Credentials-- : "+auth.getCredentials());
			for (GrantedAuthority authority : auth.getAuthorities()) {
			     String roleName = authority.getAuthority();
	                    System.out.println("==>"+auth.getName()+", You have "+ roleName);
	             Role puRole = roleService.getByRoleName(roleName);
	             if(puRole!=null && 
	            		 (uri.startsWith("/rest/")||uri.startsWith("/admin/")) && 
	            		 !uri.startsWith("/rest/combobox/") && 
	            		 !uri.startsWith("/admin/map/index")
	            		 ){
                         System.out.println("puROle ID :"+puRole.getId());
	            	 Page<RolePage> pageRolePage = rolePageService.searchWithRoleId(Integer.MAX_VALUE, 0, null, puRole.getId());
	            	 List<RolePage> listRolePage = pageRolePage.getContent();
	            	 for(RolePage rolePage:listRolePage){
	            		 PageApp puPage = rolePage.getIdPage();
                                    System.out.println("ROLE : "+puPage.getPath());
	            		 if(uri.contains("/"+puPage.getPath()+"/")){
	            			 isDenied = false;
	            		 }
	            	 }	    
	             }else{
                         System.out.println("PuROLE IS NULL");
                     }
			}
		}
		return !isDenied;
	}
}
