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
@Table(name = "rel_ppk_paket")
@NamedQueries({
    @NamedQuery(name = "RelPpkPaket.findAll", query = "SELECT r FROM RelPpkPaket r")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RelPpkPaket extends BaseModel {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserApp idUser;
    @JoinColumn(name = "id_paket", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paket idPaket;

    public RelPpkPaket() {
    }

    public RelPpkPaket(String id) {
        this.id = id;
    }

    public UserApp getIdUser() {
        return idUser;
    }

    public void setIdUser(UserApp idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof RelPpkPaket)) {
            return false;
        }
        RelPpkPaket other = (RelPpkPaket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.RelPpkPaket[ id=" + id + " ]";
    }
    
}
