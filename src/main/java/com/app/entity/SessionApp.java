/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "session_app")
@NamedQueries({
    @NamedQuery(name = "SessionApp.findAll", query = "SELECT c FROM SessionApp c")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SessionApp extends BaseModel {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "user_agent")
    private String userAgent;
    @Column(name = "device")
    private String device;
    @Column(name = "session_device")
    private String sessionDevice;
    @Column(name = "last_activity")
    private Integer lastActivity;
    @Lob
    @Column(name = "user_data")
    private byte[] userData;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "username")
    private String username;
    @Column(name = "real_name")
    private String realName;
    @Column(name = "active")
    private boolean active;
    
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private UserApp idUser;
    
    @JoinColumn(name = "id_reg_app", referencedColumnName = "id")
    @ManyToOne
    private RegApp regApp;

    public SessionApp() {
    }

    public SessionApp(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Integer getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Integer lastActivity) {
        this.lastActivity = lastActivity;
    }

    public byte[] getUserData() {
        return userData;
    }

    public void setUserData(byte[] userData) {
        this.userData = userData;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public UserApp getIdUser() {
        return idUser;
    }

    public void setIdUser(UserApp idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SessionApp)) {
            return false;
        }
        SessionApp other = (SessionApp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.CiSessions[ id=" + id + " ]";
    }

    /**
     * @return the device
     */
    public String getDevice() {
        return device;
    }

    /**
     * @param device the device to set
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the sessionDevice
     */
    public String getSessionDevice() {
        return sessionDevice;
    }

    /**
     * @param sessionDevice the sessionDevice to set
     */
    public void setSessionDevice(String sessionDevice) {
        this.sessionDevice = sessionDevice;
    }

    /**
     * @return the regApp
     */
    public RegApp getRegApp() {
        return regApp;
    }

    /**
     * @param regApp the regApp to set
     */
    public void setRegApp(RegApp regApp) {
        this.regApp = regApp;
    }
    
}
