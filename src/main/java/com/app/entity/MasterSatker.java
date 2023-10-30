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
@Table(name = "master_satker")
@NamedQueries({
    @NamedQuery(name = "MasterSatker.findAll", query = "SELECT m FROM MasterSatker m")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasterSatker extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "kode_satker")
    private String kodeSatker;
    @Column(name = "nama_satker")
    private String namaSatker;
    @Basic(optional = false)
    @Column(name = "kepala_satker")
    private String kepalaSatker;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSatker")
    private List<RelSatkerPpk> relSatkerPpkList;

    public MasterSatker() {
    }

    public MasterSatker(String id) {
        this.id = id;
    }

    public MasterSatker(String id, String kepalaSatker) {
        this.id = id;
        this.kepalaSatker = kepalaSatker;
    }

    public String getKodeSatker() {
        return kodeSatker;
    }

    public void setKodeSatker(String kodeSatker) {
        this.kodeSatker = kodeSatker;
    }

    public String getNamaSatker() {
        return namaSatker;
    }

    public void setNamaSatker(String namaSatker) {
        this.namaSatker = namaSatker;
    }

    public String getKepalaSatker() {
        return kepalaSatker;
    }

    public void setKepalaSatker(String kepalaSatker) {
        this.kepalaSatker = kepalaSatker;
    }

    public List<RelSatkerPpk> getRelSatkerPpkList() {
        return relSatkerPpkList;
    }

    public void setRelSatkerPpkList(List<RelSatkerPpk> relSatkerPpkList) {
        this.relSatkerPpkList = relSatkerPpkList;
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
        if (!(object instanceof MasterSatker)) {
            return false;
        }
        MasterSatker other = (MasterSatker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.MasterSatker[ id=" + id + " ]";
    }
    
}
