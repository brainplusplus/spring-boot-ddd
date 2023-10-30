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
import javax.persistence.Lob;
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
@Table(name = "wilayah")
@NamedQueries({
    @NamedQuery(name = "Wilayah.findAll", query = "SELECT w FROM Wilayah w")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wilayah extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "nama_wilayah")
    private String namaWilayah;
    @Lob
    @Column(name = "keterangan")
    private String keterangan;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWilayah")
    private List<RelWilayahProv> relWilayahProvList;

    public Wilayah() {
    }

    public Wilayah(String id) {
        this.id = id;
    }

    public String getNamaWilayah() {
        return namaWilayah;
    }

    public void setNamaWilayah(String namaWilayah) {
        this.namaWilayah = namaWilayah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
        if (!(object instanceof Wilayah)) {
            return false;
        }
        Wilayah other = (Wilayah) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.Wilayah[ id=" + id + " ]";
    }
    
}
