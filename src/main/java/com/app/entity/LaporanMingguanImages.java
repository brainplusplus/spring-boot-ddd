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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "laporan_mingguan_images")
@NamedQueries({
    @NamedQuery(name = "LaporanMingguanImages.findAll", query = "SELECT l FROM LaporanMingguanImages l")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LaporanMingguanImages extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "file")
    private String file;
    @Lob
    @Column(name = "keterangan")
    private String keterangan;
    @JoinColumn(name = "id_laporan", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotFound(
            action = NotFoundAction.IGNORE)
    private LaporanMingguan idLaporan;

    public LaporanMingguanImages() {
    }

    public LaporanMingguanImages(String id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public LaporanMingguan getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(LaporanMingguan idLaporan) {
        this.idLaporan = idLaporan;
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
        if (!(object instanceof LaporanMingguanImages)) {
            return false;
        }
        LaporanMingguanImages other = (LaporanMingguanImages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.LaporanMingguanImages[ id=" + id + " ]";
    }
    
}
