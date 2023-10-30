/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "kab")
@NamedQueries({
    @NamedQuery(name = "Kab.findAll", query = "SELECT k FROM Kab k")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Kab extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "nama_kab")
    private String namaKab;
    @Basic(optional = false)
    @Column(name = "kode_kab")
    private String kodeKab;

    @Basic(optional = false)
    @JoinColumn(name = "kode_prov", referencedColumnName = "kode")
    @ManyToOne(optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Prov kodeProv;
    
    @Basic(optional = true)
    @Column(name = "x_coordinate")
    private String xCoordinate;
    
    @Basic(optional = true)
    @Column(name = "y_coordinate")
    private String yCoordinate;
    
    @Basic(optional = true)
    @Column(name = "x_center_data")
    private String xCenterData;
    
    @Basic(optional = true)
    @Column(name = "y_center_data")
    private String yCenterData;

    public Kab() {
    }

    public Kab(String id) {
        this.id = id;
    }

    public Kab(String id, String namaKab, String kodeKab, Prov kodeProv) {
        this.id = id;
        this.namaKab = namaKab;
        this.kodeKab = kodeKab;
        this.kodeProv = kodeProv;
    }

    public String getNamaKab() {
        return namaKab;
    }

    public void setNamaKab(String namaKab) {
        this.namaKab = namaKab;
    }

    public String getKodeKab() {
        return kodeKab;
    }

    public void setKodeKab(String kodeKab) {
        this.kodeKab = kodeKab;
    }

    public Prov getKodeProv() {
        return kodeProv;
    }

    public void setKodeProv(Prov kodeProv) {
        this.kodeProv = kodeProv;
    }
    
    /**
     * @return the xCoordinate
     */
    public String getxCoordinate() {
        return xCoordinate;
    }

    /**
     * @param xCoordinate the xCoordinate to set
     */
    public void setxCoordinate(String xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * @return the yCoordinate
     */
    public String getyCoordinate() {
        return yCoordinate;
    }

    /**
     * @param yCoordinate the yCoordinate to set
     */
    public void setyCoordinate(String yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * @return the xCenterData
     */
    public String getxCenterData() {
        return xCenterData;
    }

    /**
     * @param xCenterData the xCenterData to set
     */
    public void setxCenterData(String xCenterData) {
        this.xCenterData = xCenterData;
    }

    /**
     * @return the yCenterData
     */
    public String getyCenterData() {
        return yCenterData;
    }

    /**
     * @param yCenterData the yCenterData to set
     */
    public void setyCenterData(String yCenterData) {
        this.yCenterData = yCenterData;
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
        if (!(object instanceof Kab)) {
            return false;
        }
        Kab other = (Kab) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.Kab[ id=" + id + " ]";
    }
    
}
