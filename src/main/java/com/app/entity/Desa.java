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
@Table(name = "desa")
@NamedQueries({
    @NamedQuery(name = "Desa.findAll", query = "SELECT d FROM Desa d")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Desa extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "kodekab")
    private String kodekab;
    @Column(name = "kodekec")
    private String kodekec;
    @Column(name = "kodedes")
    private String kodedes;
    @Column(name = "namadesa")
    private String namadesa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "x_coord")
    private Double xCoord;
    @Column(name = "y_coord")
    private Double yCoord;
    @Basic(optional = false)
    @Column(name = "tipedata")
    private boolean tipedata;

    public Desa() {
    }

    public Desa(String id) {
        this.id = id;
    }

    public Desa(String id, boolean tipedata) {
        this.id = id;
        this.tipedata = tipedata;
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

    public String getKodedes() {
        return kodedes;
    }

    public void setKodedes(String kodedes) {
        this.kodedes = kodedes;
    }

    public String getNamadesa() {
        return namadesa;
    }

    public void setNamadesa(String namadesa) {
        this.namadesa = namadesa;
    }

    public Double getXCoord() {
        return xCoord;
    }

    public void setXCoord(Double xCoord) {
        this.xCoord = xCoord;
    }

    public Double getYCoord() {
        return yCoord;
    }

    public void setYCoord(Double yCoord) {
        this.yCoord = yCoord;
    }

    public boolean getTipedata() {
        return tipedata;
    }

    public void setTipedata(boolean tipedata) {
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
        if (!(object instanceof Desa)) {
            return false;
        }
        Desa other = (Desa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.Desa[ id=" + id + " ]";
    }
    
}
