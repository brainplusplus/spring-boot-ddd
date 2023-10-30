/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller.api;

import com.app.entity.Role;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.api.request.user.ApiUserLoginRequest;
import com.app.object.api.request.user.ApiUserResendVerificationCodeRequest;
import com.app.object.api.request.user.ApiUserVerificationCodeRequest;
import com.app.service.api.ApiUserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author brainplusplus
 */
@RestController
@RequestMapping("/api/user")
public class ApiUserController {
    
    @Autowired
    private ApiUserService aus;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseObject login(HttpServletRequest request,@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "device", required = false) String device,
                        @RequestParam(value = "appId", required = false) String appId) {
        ApiUserLoginRequest req = new ApiUserLoginRequest();
        req.username = username;
        req.password = password;
        req.device = device;
        req.appId = appId;
        req.sessionId = request.getSession().getId();
        System.out.println("USERNAME:"+req.username);
        System.out.println("PASSWORD:"+req.password);
        System.out.println("DEVICE:"+req.device);
        System.out.println("TEST");
        
	ResponseObject response = new ResponseObject();
	try {
            response.data = aus.login(req);
            if(response.data==null){
                throw new Exception("Not found data, You are not in Role PENGAWAS");
            }
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
            ex.printStackTrace();
	}
	return response;
    }
    
    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    public ResponseObject verifyCode(@RequestParam(value = "sessionId", required = false) String sessionId,
			@RequestParam(value = "verificationCode", required = false) String verificationCode,
                        @RequestParam(value = "appId", required = false) String appId) {
        ApiUserVerificationCodeRequest req = new ApiUserVerificationCodeRequest();
        req.sessionId = sessionId;
        req.verificationCode = verificationCode;
        req.appId = appId;
	ResponseObject response = new ResponseObject();
	try {
            response.data = aus.verifyCode(req);
            if(response.data==null){
                throw new Exception("Not found data");
            }
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
	}
	return response;
    }
    
    @RequestMapping(value = "/resendVerificationCode", method = RequestMethod.GET)
    public ResponseObject resendVerificationCode(@RequestParam(value = "sessionId", required = false) String sessionId,
            @RequestParam(value = "appId", required = false) String appId) {
        ApiUserResendVerificationCodeRequest req = new ApiUserResendVerificationCodeRequest();
        req.sessionId = sessionId;
        req.appId = appId;
	ResponseObject response = new ResponseObject();
	try {
            response.isSuccess = aus.resendVerificationCode(req);
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
	}
	return response;
    }
    
}