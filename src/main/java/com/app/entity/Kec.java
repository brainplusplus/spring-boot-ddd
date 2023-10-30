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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "kec")
@NamedQueries({
    @NamedQuery(name = "Kec.findAll", query = "SELECT k FROM Kec k")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Kec extends BaseModel {

    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "kodekab")
    private String kodekab;
    @Basic(optional = false)
    @Column(name = "kodekec")
    private String kodekec;
    @Column(name = "namakec")
    private String namakec;
    @Column(name = "tipedata")
    private Boolean tipedata;

    public Kec() {
    }

    public Kec(String id) {
        this.id = id;
    }

    public Kec(String id, String kodekab, String kodekec) {
        this.id = id;
        this.kodekab = kodekab;
        this.kodekec = kodekec;
    }

    public String getKodekab() {
        return kodekab;
    }

    public void setKodekab(String kodekab) {
        this.kodekab = kodekab;
    }

    public String getKodekec() {
        return kodekec;
    }

    public void setKodekec(String kodekec) {
        this.kodekec = kodekec;
    }

    public String getNamakec() {
        return namakec;
    }

    public void setNamakec(String namakec) {
        this.namakec = namakec;
    }

    public Boolean getTipedata() {
        return tipedata;
    }

    public void setTipedata(Boolean tipedata) {
        this.tipedata = tipedata;
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
        if (!(object instanceof Kec)) {
            return false;
        }
        Kec other = (Kec) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.Kec[ id=" + id + " ]";
    }
    
}
