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
@Table(name = "rel_satker_ppk")
@NamedQueries({
    @NamedQuery(name = "RelSatkerPpk.findAll", query = "SELECT r FROM RelSatkerPpk r")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RelSatkerPpk extends BaseModel {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "id_satker", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MasterSatker idSatker;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserApp idUser;

    public RelSatkerPpk() {
    }

    public RelSatkerPpk(String id) {
        this.id = id;
    }

    public MasterSatker getIdSatker() {
        return idSatker;
    }

    public void setIdSatker(MasterSatker idSatker) {
        this.idSatker = idSatker;
    }

    public UserApp getIdUser() {
        return idUser;
    }

    public void setIdUser(UserApp idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof RelSatkerPpk)) {
            return false;
        }
        RelSatkerPpk other = (RelSatkerPpk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.RelSatkerPpk[ id=" + id + " ]";
    }
    
}
