/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.OneToMany;
/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "reg_app")
@NamedQueries({
    @NamedQuery(name = "RegApp.findAll", query = "SELECT d FROM RegApp d")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegApp extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy = "regApp")
    private List<SessionApp> sessionAppList;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sessionAppList
     */
    public List<SessionApp> getSessionAppList() {
        return sessionAppList;
    }

    /**
     * @param sessionAppList the sessionAppList to set
     */
    public void setSessionAppList(List<SessionApp> sessionAppList) {
        this.sessionAppList = sessionAppList;
    }
    
}
