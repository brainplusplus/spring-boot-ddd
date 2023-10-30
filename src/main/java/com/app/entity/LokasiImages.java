/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "lokasi_images")
@NamedQueries({
    @NamedQuery(name = "LokasiImages.findAll", query = "SELECT l FROM LokasiImages l")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LokasiImages extends BaseModel {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "nama_file")
    private String namaFile;
    @Column(name = "file")
    private String file;
    @Lob
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "type")
    private Integer type;
    @JoinColumn(name = "id_lokasi", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LokasiSurvey idLokasi;

    public LokasiImages() {
    }

    public LokasiImages(String id) {
        this.id = id;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LokasiSurvey getIdLokasi() {
        return idLokasi;
    }

    public void setIdLokasi(LokasiSurvey idLokasi) {
        this.idLokasi = idLokasi;
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
        if (!(object instanceof LokasiImages)) {
            return false;
        }
        LokasiImages other = (LokasiImages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.LokasiImages[ id=" + id + " ]";
    }
    
}
