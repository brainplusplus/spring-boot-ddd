/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "prov")
@NamedQueries({
    @NamedQuery(name = "Prov.findAll", query = "SELECT p FROM Prov p")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Prov extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "kode")
    private String kode;
    @Column(name = "x_coord")
    private String xCoord;
    @Column(name = "y_coord")
    private String yCoord;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProv")
    private List<RelWilayahProv> relWilayahProvList;

    public Prov() {
    }

    public Prov(String id) {
        this.id = id;
    }

    public Prov(String id, String nama, String kode) {
        this.id = id;
        this.nama = nama;
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
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

    public List<RelWilayahProv> getRelWilayahProvList() {
        return relWilayahProvList;
    }

    public void setRelWilayahProvList(List<RelWilayahProv> relWilayahProvList) {
        this.relWilayahProvList = relWilayahProvList;
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
        if (!(object instanceof Prov)) {
            return false;
        }
        Prov other = (Prov) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.Prov[ id=" + id + " ]";
    }
    
}
