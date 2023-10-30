/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.object.api.response.user;

import com.app.entity.UserApp;

/**
 *
 * @author brainplusplus
 */
public class ApiUserVerificationCodeResponse {
    public String token;
    public UserApp user;
    public String realName;
    //public UserApp realName;
}
