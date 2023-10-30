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
@Table(name = "logo_mk")
@NamedQueries({
    @NamedQuery(name = "LogoMk.findAll", query = "SELECT l FROM LogoMk l")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LogoMk extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "nama_mk")
    private String namaMk;
    @Column(name = "file_logo")
    private String fileLogo;

    public LogoMk() {
    }

    public LogoMk(String id) {
        this.id = id;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public String getFileLogo() {
        return fileLogo;
    }

    public void setFileLogo(String fileLogo) {
        this.fileLogo = fileLogo;
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
        if (!(object instanceof LogoMk)) {
            return false;
        }
        LogoMk other = (LogoMk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.LogoMk[ id=" + id + " ]";
    }
    
}
