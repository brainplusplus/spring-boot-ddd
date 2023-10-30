/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service.master;

import com.app.dao.SessionAppDao;
import com.app.entity.SessionApp;
import com.app.entity.UserApp;
import com.app.service.GenericDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brainplusplus
 */
@Service
public class SessionAppService extends GenericDAOService<SessionAppDao, SessionApp> {
    @Autowired
    private SessionAppDao sad;
    
    public SessionApp getSessionById(String id){
        return sad.findById(id);
    }
    
    public SessionApp getSessionByToken(String token){
        return getSessionById(token);
    }
    
    public SessionApp getSessionBySessionDeviceAndVerificationCode(String sessionId, String verificationCode){
        return sad.findBySessionDeviceAndVerificationCode(sessionId, verificationCode);
    }
    
    public SessionApp getSessionBySessionDevice(String sessionId){
        return sad.findBySessionDevice(sessionId);
    }
    
    public SessionApp getSessionBySessionDeviceAndDevice(String sessionId,String device){
        return sad.findBySessionDeviceAndDevice(sessionId,device);
    }
    
    public void removeSessionBySessionDevice(String sessionId){
        sad.removeBySessionDevice(sessionId);
    }
    
    public void removeSessionBySessionDeviceAndDevice(String sessionId,String device){
        sad.removeBySessionDeviceAndDevice(sessionId,device);
    }
}
