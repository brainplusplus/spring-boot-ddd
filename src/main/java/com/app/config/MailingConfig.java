/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.config;

import org.springframework.beans.factory.annotation.Value;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.mail.javamail.JavaMailSender;  
import org.springframework.mail.javamail.JavaMailSenderImpl;  
import java.util.Properties;  

@Configuration  
public class MailingConfig {  
    @Value("${mail.protocol}") // this is to read variable from application.properties  
    private String mailProtocol;  
    @Value("${mail.smtp.host}")  
    private String host;  
    @Value("${mail.smtp.port}")  
    private Integer port;  
    @Value("${mail.support.username}")  
    private String userName;  
    @Value("${mail.support.password}")  
    private String password;  
    @Bean  
    public JavaMailSender javaMailSender() {  
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();  
        javaMailSender.setProtocol(mailProtocol);  
        javaMailSender.setHost(host);  
        javaMailSender.setPort(port);  
        javaMailSender.setUsername(userName);  
        javaMailSender.setPassword(password);  
        javaMailSender.setJavaMailProperties(getMailProperties());  
        return javaMailSender;  
    }  
    private Properties getMailProperties() {  
        Properties properties = new Properties();  
        properties.setProperty("mail.smtp.auth", "true");  
        properties.setProperty("mail.smtp.starttls.enable", "true");  
        properties.setProperty("mail.debug", "false");  
        return properties;  
    }  
}  
