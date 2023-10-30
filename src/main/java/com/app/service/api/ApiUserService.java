/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service.api;

import com.app.entity.RegApp;
import com.app.entity.Role;
import com.app.entity.SessionApp;
import com.app.entity.UserApp;
import com.app.object.ResponseObject;
import com.app.object.api.request.user.ApiUserLoginRequest;
import com.app.object.api.request.user.ApiUserResendVerificationCodeRequest;
import com.app.object.api.request.user.ApiUserVerificationCodeRequest;
import com.app.object.api.response.user.ApiUserLoginResponse;
import com.app.object.api.response.user.ApiUserVerificationCodeResponse;
import com.app.service.master.SessionAppService;
import com.app.service.master.UserService;
import com.app.service.EmailService;
import com.app.service.master.RegAppService;
import com.app.service.master.RoleService;
import com.app.util.RandomGenerator;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brainplusplus
 */
@Service
public class ApiUserService {

    @Autowired
    private EmailService es;

    @Autowired
    private UserService us;

    @Autowired
    private SessionAppService sas;

    @Autowired
    private RegAppService ras;

    @Autowired
    private RoleService rs;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public ApiUserLoginResponse login(ApiUserLoginRequest param) {
        ApiUserLoginResponse resp = null;
        RegApp regApp = null;
        if (param.appId != null) {
            regApp = ras.getById(param.appId);
        }
        if (us.validLogin(param.username, param.password) && regApp != null) {
            //SessionApp session = sas.getSessionBySessionDeviceAndDevice(param.sessionId,param.device);
            sas.removeSessionBySessionDeviceAndDevice(param.sessionId, param.device);
            UserApp user = us.getUserByUsername(param.username);
            Role role = rs.findOne(user.getRole().getId());
            if (role.getNama().equals("PENGAWAS")) {
                //if(session==null)
                SessionApp session = new SessionApp();
                if (true) {
                    //session = new SessionApp();
                    session.setId(UUID.randomUUID().toString());
                    session.setIdUser(user);
                    session.setDevice(param.device);
                    session.setUsername(user.getUsername());
                    session.setRealName(user.getRealName());
                    session.setSessionDevice(param.sessionId);//UUID.randomUUID().toString());
                    //session.setVerificationCode(new RandomGenerator().generateRandomString(6));
                    session.setVerificationCode("201601");
                    session.setActive(false);
                    session.setRegApp(regApp);
                    em.persist(session);
                }
//            else{
//                session.setVerificationCode(new RandomGenerator().generateRandomString(6));
//                em.merge(session);
//            }
                //es.sendEmailConfirmationCode("Confirmation Code", user.getEmail(), session, user);
                resp = new ApiUserLoginResponse();
                resp.sessionId = session.getSessionDevice();
            } else {
                resp = null;
            }
        }
        return resp;
    }

    @Transactional
    public ApiUserVerificationCodeResponse verifyCode(ApiUserVerificationCodeRequest param) {
        ApiUserVerificationCodeResponse resp = null;
        RegApp regApp = null;
        if (param.appId != null) {
            regApp = ras.getById(param.appId);
        }
        SessionApp session = sas.getSessionBySessionDeviceAndVerificationCode(param.sessionId, param.verificationCode);
        if (session != null && regApp != null) {
            session.setActive(true);
            em.merge(session);
            resp = new ApiUserVerificationCodeResponse();
            resp.token = session.getId();
            resp.user = session.getIdUser();
            resp.realName = session.getRealName();
        }
        return resp;
    }

    @Transactional
    public boolean resendVerificationCode(ApiUserResendVerificationCodeRequest param) {
        ApiUserVerificationCodeResponse resp = null;
        RegApp regApp = null;
        if (param.appId != null) {
            regApp = ras.getById(param.appId);
        }
        SessionApp session = sas.getSessionBySessionDevice(param.sessionId);
        if (session != null && regApp != null) {
            session.setVerificationCode(new RandomGenerator().generateRandomString(6));
            em.merge(session);
            UserApp user = session.getIdUser();
            es.sendEmailConfirmationCode("Confirmation Code", user.getEmail(), session, user);
            return true;
        }
        return false;
    }
}
