/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.object.api.request.user;

/**
 *
 * @author brainplusplus
 */
public class ApiUserVerificationCodeRequest {
    public String sessionId;
    public String verificationCode;
    public String appId;
}
