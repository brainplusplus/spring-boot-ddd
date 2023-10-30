/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service.api;

import com.app.entity.RegApp;
import com.app.entity.SessionApp;
import com.app.service.master.RegAppService;
import com.app.service.master.SessionAppService;
import com.app.service.master.UserService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brainplusplus
 */
@Service
public class ApiCommonService {
    
    @Autowired
    private UserService us;
    
    @Autowired
    private SessionAppService sas;

    @Autowired
    private RegAppService ras;
    
    @PersistenceContext
    private EntityManager em;
        
    public boolean isValidRegAppId(String appId){
        RegApp regApp = null;
        if(appId!=null){
            regApp = ras.getById(appId);
        }
        return regApp != null;
    }
    
    public boolean isValidToken(String token){
        SessionApp session = null;
        if(token!=null){
            session = sas.getSessionById(token);
        }
        return session != null;
    }
}
