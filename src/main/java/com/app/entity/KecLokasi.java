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
@Table(name = "kec_lokasi")
@NamedQueries({
    @NamedQuery(name = "KecLokasi.findAll", query = "SELECT k FROM KecLokasi k")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KecLokasi extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "x_coord")
    private String xCoord;
    @Column(name = "y_coord")
    private String yCoord;

    public KecLokasi() {
    }

    public KecLokasi(String id) {
        this.id = id;
    }

    public String getXCoord() {
        return xCoord;
    }

    public void setXCoord(String xCoord) {
        this.xCoord = xCoord;
    }

    public String getYCoord() {
        return yCoord;
    }

    public void setYCoord(String yCoord) {
        this.yCoord = yCoord;
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
        if (!(object instanceof KecLokasi)) {
            return false;
        }
        KecLokasi other = (KecLokasi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.KecLokasi[ id=" + id + " ]";
    }
    
}
