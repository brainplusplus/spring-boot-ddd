/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.app.entity.SessionApp;
import com.app.entity.UserApp;
import java.util.HashMap;
import org.apache.velocity.app.VelocityEngine;  
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;
import javax.mail.internet.MimeMessage;  
import org.springframework.stereotype.Service;
/**
 *
 * @author brainplusplus
 */
@Service
public class EmailService {
    private static final String CHARSET_UTF8 = "UTF-8";  
    @Autowired  
    private JavaMailSender javaMailSender;  
    
    @Autowired
    private VelocityEngine velocityEngine;
    
    public void sendEmailConfirmationCode(String subject, String to, SessionApp session, UserApp user){
        MimeMessagePreparator preparator = new MimeMessagePreparator() {  
           @Override  
            public void prepare(MimeMessage mimeMessage) throws Exception {  
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage); 
            message.setFrom("kostrad.monitoring@gmail.com");
            message.setTo(to);  
            message.setSubject(subject);  
            Map model = new HashMap<>();  
            model.put("user", user);  
            model.put("session",session);
            message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine  
                , "verification-code.vm", CHARSET_UTF8, model), true);  
            }  
        };  
        this.javaMailSender.send(preparator);  
    }
}
