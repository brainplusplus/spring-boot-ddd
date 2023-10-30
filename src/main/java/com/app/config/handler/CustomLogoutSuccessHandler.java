/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.config.handler;

/**
 *
 * @author brainplusplus
 */
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.web.authentication.logout.LogoutHandler;
 
public class CustomLogoutSuccessHandler implements LogoutHandler{

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        try {
            if (authentication != null && authentication.getDetails() != null) {
                try {
                    httpServletRequest.getSession().invalidate();
                    System.out.println("User Successfully Logout");
                    //you can add more codes here when the user successfully logs out,
                    //such as updating the database for last active.
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            //redirect to login
            httpServletResponse.sendRedirect("/login");
        } catch (IOException ex) {
            Logger.getLogger(CustomLogoutSuccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
