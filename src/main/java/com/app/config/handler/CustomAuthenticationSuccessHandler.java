/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.config.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	public CustomAuthenticationSuccessHandler() {
	}
//
	/**
	 * Constructor which sets the <tt>defaultTargetUrl</tt> property of the base class.
	 * @param defaultTargetUrl the URL to which the user should be redirected on
	 * successful authentication.
	 */
	public CustomAuthenticationSuccessHandler(String defaultTargetUrl) {
		setDefaultTargetUrl(defaultTargetUrl);
	}

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
     
    @Override
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        System.out.println("LOGIN SUKSES");
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
        
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
     
    protected String determineTargetUrl(Authentication authentication) {
        String url="";
         
//        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
//         
//        List<String> roles = new ArrayList<String>();
// 
//        for (GrantedAuthority a : authorities) {
//            roles.add(a.getAuthority());
//        }
 
        url = "/admin/map/index";
 
        return url;
    }
  
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}