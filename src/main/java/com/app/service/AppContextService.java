package com.app.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.entity.UserApp;

@Service
public class AppContextService {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDao userDao;

	private Authentication auth;

	public Authentication getAuth(){
		if(auth==null){
			//auth = SecurityContextHolder.getContext().getAuthentication();
			initAuthBySession();
		}
		return auth;
	}
	public void setAuth(Authentication auth){
		this.auth = auth;
	}
	public void setAuthBySession(HttpSession session){
		if(session!=null){
			if(session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
				SecurityContextImpl context = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
				this.auth = context.getAuthentication();
			}
		}
	}
	
	public void initAuthBySession(){
		if(session!=null){
			if(session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
				SecurityContextImpl context = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
				this.auth = context.getAuthentication();
			}
		}
	}
	
	public UserApp getUserByUsername(String username){
		return userDao.findByUsername(username);
	}
	
	public String getUsername(){
		return getAuth()==null?null:getAuth().getName();
	}
	public Object getUser(){
		return getAuth()==null?null:getAuth().getPrincipal();
	}
}
