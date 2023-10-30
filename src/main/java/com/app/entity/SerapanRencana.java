/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "serapan_rencana")
@NamedQueries({
    @NamedQuery(name = "SerapanRencana.findAll", query = "SELECT s FROM SerapanRencana s")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SerapanRencana extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "tanggal_rencana")
    @Temporal(TemporalType.DATE)
    private Date tanggalRencana;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "persen_rencana")
    private Float persenRencana;
    @Column(name = "nilai_rencana")
    private BigInteger nilaiRencana;
    @Lob
    @Column(name = "keterangan")
    private String keterangan;
    @JoinColumn(name = "id_paket", referencedColumnName = "id")
    @ManyToOne
    private Paket idPaket;

    public SerapanRencana() {
    }

    public SerapanRencana(String id) {
        this.id = id;
    }

    public Date getTanggalRencana() {
        return tanggalRencana;
    }

    public void setTanggalRencana(Date tanggalRencana) {
        this.tanggalRencana = tanggalRencana;
    }

    public Float getPersenRencana() {
        return persenRencana;
    }

    public void setPersenRencana(Float persenRencana) {
        this.persenRencana = persenRencana;
    }

    public BigInteger getNilaiRencana() {
        return nilaiRencana;
    }

    public void setNilaiRencana(BigInteger nilaiRencana) {
        this.nilaiRencana = nilaiRencana;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
        if (!(object instanceof SerapanRencana)) {
            return false;
        }
        SerapanRencana other = (SerapanRencana) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.SerapanRencana[ id=" + id + " ]";
    }
    
}
