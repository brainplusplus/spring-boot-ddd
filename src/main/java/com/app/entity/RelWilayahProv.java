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
@Table(name = "rel_wilayah_prov")
@NamedQueries({
    @NamedQuery(name = "RelWilayahProv.findAll", query = "SELECT r FROM RelWilayahProv r")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RelWilayahProv extends BaseModel {

    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name = "id_wilayah", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Wilayah idWilayah;
    @JoinColumn(name = "id_prov", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Prov idProv;
    @JoinColumn(name = "id_paket", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paket idPaket;

    public RelWilayahProv() {
    }

    public RelWilayahProv(String id) {
        this.id = id;
    }

    public Wilayah getIdWilayah() {
        return idWilayah;
    }

    public void setIdWilayah(Wilayah idWilayah) {
        this.idWilayah = idWilayah;
    }

    public Prov getIdProv() {
        return idProv;
    }

    public void setIdProv(Prov idProv) {
        this.idProv = idProv;
    }

    public Paket getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(Paket idPaket) {
        this.idPaket = idPaket;
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
        if (!(object instanceof RelWilayahProv)) {
            return false;
        }
        RelWilayahProv other = (RelWilayahProv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.RelWilayahProv[ id=" + id + " ]";
    }
    
}
