/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.app.entity.statik.JenisRealisasiStatik;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "serapan_realisasi")
@NamedQueries({
    @NamedQuery(name = "SerapanRealisasiEntity.findAll", query = "SELECT s FROM SerapanRealisasiEntity s")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SerapanRealisasiEntity extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "tanggal_realisasi")
    @Temporal(TemporalType.DATE)
    private Date tanggalRealisasi;

    @Column(name = "jenis_realisasi")
    private String jenisRealisasi;
    @Column(name = "kop_realisasi")
    private String kopRealisasi;

    @Column(name = "persen_realisasi")
    private Float persenRealisasi;
    @Column(name = "nilai_realisasi")
    private BigInteger nilaiRealisasi;
    @Column(name = "total_persen_realisasi")
    private Float totalPersenRealisasi;
    @Lob
    @Column(name = "keterangan")
    private String keterangan;

    @JoinColumn(name = "id_lokasi", referencedColumnName = "id")
    @ManyToOne(optional = false)
//    @Column(name = "id_lokasi")
    private LokasiSurvey idLokasi;

    public SerapanRealisasiEntity() {
    }

    public SerapanRealisasiEntity(String id) {
        this.id = id;
    }

    public Date getTanggalRealisasi() {
        return tanggalRealisasi;
    }

    public void setTanggalRealisasi(Date tanggalRealisasi) {
        this.tanggalRealisasi = tanggalRealisasi;
    }

    public Float getPersenRealisasi() {
        return persenRealisasi;
    }

    public void setPersenRealisasi(Float persenRealisasi) {
        this.persenRealisasi = persenRealisasi;
    }

    public BigInteger getNilaiRealisasi() {
        return nilaiRealisasi;
    }

    public void setNilaiRealisasi(BigInteger nilaiRealisasi) {
        this.nilaiRealisasi = nilaiRealisasi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
        if (!(object instanceof SerapanRealisasiEntity)) {
            return false;
        }
        SerapanRealisasiEntity other = (SerapanRealisasiEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.SerapanRealisasiEntity[ id=" + id + " ]";
    }

    public String getJenisRealisasi() {
        return jenisRealisasi;
    }

    public void setJenisRealisasi(String jenisRealisasi) {
        this.jenisRealisasi = jenisRealisasi;
    }

    public Map<String, String> getListJenisRealisasi() {
        return JenisRealisasiStatik.getListJenisRealisasi();
    }

    public String getKopRealisasi() {
        return kopRealisasi;
    }

    public void setKopRealisasi(String kopRealisasi) {
        this.kopRealisasi = kopRealisasi;
    }

    public LokasiSurvey getIdLokasi() {
        return idLokasi;
    }

    public void setIdLokasi(LokasiSurvey idLokasi) {
        this.idLokasi = idLokasi;
    }

    public Float getTotalPersenRealisasi() {
        return totalPersenRealisasi;
    }

    public void setTotalPersenRealisasi(Float totalPersenRealisasi) {
        this.totalPersenRealisasi = totalPersenRealisasi;
    }
}
