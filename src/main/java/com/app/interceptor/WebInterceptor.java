package com.app.interceptor;

import com.app.entity.PageApp;
import com.app.entity.Role;
import com.app.entity.RolePage;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.app.object.ResponseObject;
import com.app.service.AppContextService;
import com.app.service.master.RolePageService;
import com.app.service.master.RoleService;
import com.app.service.master.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebInterceptor implements HandlerInterceptor {
	
	
	@Autowired
	private AppContextService appCtx;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RolePageService rolePageService;	

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}
		appCtx.setAuthBySession(request.getSession());
		System.out.println("--WEB INTERCEPTOR--");
		System.out.println("--URL-- : "+request.getRequestURI());
		System.out.println("--Name-- : "+appCtx.getUsername());
		Authentication auth = appCtx.getAuth();
		if(auth!=null){
			System.out.println("--Auth-- : "+auth.isAuthenticated());
			System.out.println("--Credentials-- : "+auth.getCredentials());
			if(!auth.isAuthenticated()){
			 response.sendRedirect("/login?msg=You need login first!");
   			 return false;
			}
			for (GrantedAuthority authority : auth.getAuthorities()) {
			     String roleName = authority.getAuthority();
	                    System.out.println("==>"+auth.getName()+", You have "+ roleName);
	             Role puRole = roleService.getByRoleName(roleName);
	             String uri = request.getRequestURI();
	             if(puRole!=null && 
	            		 (uri.startsWith("/rest/")||uri.startsWith("/admin/")) && 
	            		 !uri.startsWith("/rest/combobox/") && 
	            		 !uri.startsWith("/admin/map/index")
	            		 ){
	            	 Page<RolePage> pageRolePage = rolePageService.searchWithRoleId(Integer.MAX_VALUE, 0, null, puRole.getId());
	            	 List<RolePage> listRolePage = pageRolePage.getContent();
	            	 boolean isDenied = true;
	            	 for(RolePage rolePage:listRolePage){
	            		 PageApp puPage = rolePage.getIdPage();
	            		 if(uri.contains("/"+puPage.getPath()+"/")){
	            			 isDenied = false;
	            		 }
	            	 }
	            	 if(isDenied){
	            		 //REST
	            		 if(uri.startsWith("/rest/")){
	            			 //System.out.println("Access is Denied REST");
	            			 if(true){
	            				 response.setContentType("application/json");
	            				// Get the printwriter object from response to write the required json object to the output stream      
	            				 ObjectMapper mapper = new ObjectMapper();
	            				 PrintWriter out = response.getWriter();
	            				// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
	            				ResponseObject respJson = new ResponseObject();
	            				respJson.isSuccess = false;
	            				respJson.data = null;
	            				respJson.message = "Access is Denied";
	            				out.print( mapper.writeValueAsString(respJson));
	            				out.flush();
	            			 }
	            			 if(false)
	            			 response.sendError(HttpServletResponse.SC_FORBIDDEN,
	         						"Access is Denied");
	            			 if(false)
	            			 response.sendRedirect("/access_denied?msg=Access is denied");
	            			 return false;
	            		 }
	            		 //WEB
	            		 else{
	            			 //System.out.println("Access is Denied WEB");
	            			 if(false)
	            			 response.sendError(HttpServletResponse.SC_FORBIDDEN,
	         						"Access is Denied");
	            			 if(true)
	            			 response.sendRedirect("/access_denied?msg=Access is denied");
	            			 return false;
	            		 }
	            	 }
	             }
			}
		}
		
				
		if (request.getMethod().equalsIgnoreCase("GET")) {
			// GET - allow the request
			return true;
		} else {
			// This is a POST request - need to check the CSRF token
			if (true) {
				return true;
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN,
						"Bad or missing CSRF value");
				return false;
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
