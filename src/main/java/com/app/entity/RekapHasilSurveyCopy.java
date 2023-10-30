/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "rekap_hasil_survey_copy")
@NamedQueries({
    @NamedQuery(name = "RekapHasilSurveyCopy.findAll", query = "SELECT r FROM RekapHasilSurveyCopy r")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RekapHasilSurveyCopy extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "nama_surveyor")
    private String namaSurveyor;
    @Column(name = "nama_konsultan_mk")
    private String namaKonsultanMk;
    @Column(name = "tanggal_survey")
    @Temporal(TemporalType.DATE)
    private Date tanggalSurvey;
    @Column(name = "lat_deg")
    private String latDeg;
    @Column(name = "lat_min")
    private String latMin;
    @Column(name = "lat_sec")
    private String latSec;
    @Column(name = "lat_dir")
    private String latDir;
    @Column(name = "long_deg")
    private String longDeg;
    @Column(name = "long_min")
    private String longMin;
    @Column(name = "long_sec")
    private String longSec;
    @Column(name = "long_dir")
    private String longDir;
    @Column(name = "nama_lembaga_pengusul")
    private String namaLembagaPengusul;
    @Lob
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "kontak_person")
    private String kontakPerson;
    @Column(name = "target_grup")
    private String targetGrup;
    @Column(name = "jml_tb")
    private String jmlTb;
    @Lob
    @Column(name = "lebar_jalan_kondisi_akses")
    private String lebarJalanKondisiAkses;
    @Column(name = "sumber_listrik_jarak_lokasi")
    private String sumberListrikJarakLokasi;
    @Column(name = "sumber_air_jarak_lokasi")
    private String sumberAirJarakLokasi;
    @Column(name = "jarak_dengan_pusat_kegiatan")
    private String jarakDenganPusatKegiatan;
    @Column(name = "luas_lahan_usulan")
    private String luasLahanUsulan;
    @Column(name = "status_tanah")
    private String statusTanah;
    @Column(name = "jenis_tanah")
    private String jenisTanah;
    @Column(name = "kondisi_fisik")
    private String kondisiFisik;
    @Column(name = "foto_kondisi_lahan_1")
    private String fotoKondisiLahan1;
    @Column(name = "foto_kondisi_lahan_2")
    private String fotoKondisiLahan2;
    @Column(name = "foto_kondisi_lahan_3")
    private String fotoKondisiLahan3;
    @Column(name = "foto_kondisi_lahan_4")
    private String fotoKondisiLahan4;
    @Column(name = "foto_kondisi_jalan_1")
    private String fotoKondisiJalan1;
    @Column(name = "foto_kondisi_jalan_2")
    private String fotoKondisiJalan2;
    @Column(name = "foto_kondisi_jalan_3")
    private String fotoKondisiJalan3;
    @Column(name = "foto_kondisi_jalan_4")
    private String fotoKondisiJalan4;
    @Column(name = "foto_kondisi_sumber_air_1")
    private String fotoKondisiSumberAir1;
    @Column(name = "foto_kondisi_sumber_air_2")
    private String fotoKondisiSumberAir2;
    @Column(name = "foto_kondisi_sumber_air_3")
    private String fotoKondisiSumberAir3;
    @Column(name = "foto_kondisi_sumber_air_4")
    private String fotoKondisiSumberAir4;
    @Lob
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "rekomendasi")
    private String rekomendasi;

    public RekapHasilSurveyCopy() {
    }

    public RekapHasilSurveyCopy(String id) {
        this.id = id;
    }

    public String getNamaSurveyor() {
        return namaSurveyor;
    }

    public void setNamaSurveyor(String namaSurveyor) {
        this.namaSurveyor = namaSurveyor;
    }

    public String getNamaKonsultanMk() {
        return namaKonsultanMk;
    }

    public void setNamaKonsultanMk(String namaKonsultanMk) {
        this.namaKonsultanMk = namaKonsultanMk;
    }

    public Date getTanggalSurvey() {
        return tanggalSurvey;
    }

    public void setTanggalSurvey(Date tanggalSurvey) {
        this.tanggalSurvey = tanggalSurvey;
    }

    public String getLatDeg() {
        return latDeg;
    }

    public void setLatDeg(String latDeg) {
        this.latDeg = latDeg;
    }

    public String getLatMin() {
        return latMin;
    }

    public void setLatMin(String latMin) {
        this.latMin = latMin;
    }

    public String getLatSec() {
        return latSec;
    }

    public void setLatSec(String latSec) {
        this.latSec = latSec;
    }

    public String getLatDir() {
        return latDir;
    }

    public void setLatDir(String latDir) {
        this.latDir = latDir;
    }

    public String getLongDeg() {
        return longDeg;
    }

    public void setLongDeg(String longDeg) {
        this.longDeg = longDeg;
    }

    public String getLongMin() {
        return longMin;
    }

    public void setLongMin(String longMin) {
        this.longMin = longMin;
    }

    public String getLongSec() {
        return longSec;
    }

    public void setLongSec(String longSec) {
        this.longSec = longSec;
    }

    public String getLongDir() {
        return longDir;
    }

    public void setLongDir(String longDir) {
        this.longDir = longDir;
    }

    public String getNamaLembagaPengusul() {
        return namaLembagaPengusul;
    }

    public void setNamaLembagaPengusul(String namaLembagaPengusul) {
        this.namaLembagaPengusul = namaLembagaPengusul;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKontakPerson() {
        return kontakPerson;
    }

    public void setKontakPerson(String kontakPerson) {
        this.kontakPerson = kontakPerson;
    }

    public String getTargetGrup() {
        return targetGrup;
    }

    public void setTargetGrup(String targetGrup) {
        this.targetGrup = targetGrup;
    }

    public String getJmlTb() {
        return jmlTb;
    }

    public void setJmlTb(String jmlTb) {
        this.jmlTb = jmlTb;
    }

    public String getLebarJalanKondisiAkses() {
        return lebarJalanKondisiAkses;
    }

    public void setLebarJalanKondisiAkses(String lebarJalanKondisiAkses) {
        this.lebarJalanKondisiAkses = lebarJalanKondisiAkses;
    }

    public String getSumberListrikJarakLokasi() {
        return sumberListrikJarakLokasi;
    }

    public void setSumberListrikJarakLokasi(String sumberListrikJarakLokasi) {
        this.sumberListrikJarakLokasi = sumberListrikJarakLokasi;
    }

    public String getSumberAirJarakLokasi() {
        return sumberAirJarakLokasi;
    }

    public void setSumberAirJarakLokasi(String sumberAirJarakLokasi) {
        this.sumberAirJarakLokasi = sumberAirJarakLokasi;
    }

    public String getJarakDenganPusatKegiatan() {
        return jarakDenganPusatKegiatan;
    }

    public void setJarakDenganPusatKegiatan(String jarakDenganPusatKegiatan) {
        this.jarakDenganPusatKegiatan = jarakDenganPusatKegiatan;
    }

    public String getLuasLahanUsulan() {
        return luasLahanUsulan;
    }

    public void setLuasLahanUsulan(String luasLahanUsulan) {
        this.luasLahanUsulan = luasLahanUsulan;
    }

    public String getStatusTanah() {
        return statusTanah;
    }

    public void setStatusTanah(String statusTanah) {
        this.statusTanah = statusTanah;
    }

    public String getJenisTanah() {
        return jenisTanah;
    }

    public void setJenisTanah(String jenisTanah) {
        this.jenisTanah = jenisTanah;
    }

    public String getKondisiFisik() {
        return kondisiFisik;
    }

    public void setKondisiFisik(String kondisiFisik) {
        this.kondisiFisik = kondisiFisik;
    }

    public String getFotoKondisiLahan1() {
        return fotoKondisiLahan1;
    }

    public void setFotoKondisiLahan1(String fotoKondisiLahan1) {
        this.fotoKondisiLahan1 = fotoKondisiLahan1;
    }

    public String getFotoKondisiLahan2() {
        return fotoKondisiLahan2;
    }

    public void setFotoKondisiLahan2(String fotoKondisiLahan2) {
        this.fotoKondisiLahan2 = fotoKondisiLahan2;
    }

    public String getFotoKondisiLahan3() {
        return fotoKondisiLahan3;
    }

    public void setFotoKondisiLahan3(String fotoKondisiLahan3) {
        this.fotoKondisiLahan3 = fotoKondisiLahan3;
    }

    public String getFotoKondisiLahan4() {
        return fotoKondisiLahan4;
    }

    public void setFotoKondisiLahan4(String fotoKondisiLahan4) {
        this.fotoKondisiLahan4 = fotoKondisiLahan4;
    }

    public String getFotoKondisiJalan1() {
        return fotoKondisiJalan1;
    }

    public void setFotoKondisiJalan1(String fotoKondisiJalan1) {
        this.fotoKondisiJalan1 = fotoKondisiJalan1;
    }

    public String getFotoKondisiJalan2() {
        return fotoKondisiJalan2;
    }

    public void setFotoKondisiJalan2(String fotoKondisiJalan2) {
        this.fotoKondisiJalan2 = fotoKondisiJalan2;
    }

    public String getFotoKondisiJalan3() {
        return fotoKondisiJalan3;
    }

    public void setFotoKondisiJalan3(String fotoKondisiJalan3) {
        this.fotoKondisiJalan3 = fotoKondisiJalan3;
    }

    public String getFotoKondisiJalan4() {
        return fotoKondisiJalan4;
    }

    public void setFotoKondisiJalan4(String fotoKondisiJalan4) {
        this.fotoKondisiJalan4 = fotoKondisiJalan4;
    }

    public String getFotoKondisiSumberAir1() {
        return fotoKondisiSumberAir1;
    }

    public void setFotoKondisiSumberAir1(String fotoKondisiSumberAir1) {
        this.fotoKondisiSumberAir1 = fotoKondisiSumberAir1;
    }

    public String getFotoKondisiSumberAir2() {
        return fotoKondisiSumberAir2;
    }

    public void setFotoKondisiSumberAir2(String fotoKondisiSumberAir2) {
        this.fotoKondisiSumberAir2 = fotoKondisiSumberAir2;
    }

    public String getFotoKondisiSumberAir3() {
        return fotoKondisiSumberAir3;
    }

    public void setFotoKondisiSumberAir3(String fotoKondisiSumberAir3) {
        this.fotoKondisiSumberAir3 = fotoKondisiSumberAir3;
    }

    public String getFotoKondisiSumberAir4() {
        return fotoKondisiSumberAir4;
    }

    public void setFotoKondisiSumberAir4(String fotoKondisiSumberAir4) {
        this.fotoKondisiSumberAir4 = fotoKondisiSumberAir4;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getRekomendasi() {
        return rekomendasi;
    }

    public void setRekomendasi(String rekomendasi) {
        this.rekomendasi = rekomendasi;
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
        if (!(object instanceof RekapHasilSurveyCopy)) {
            return false;
        }
        RekapHasilSurveyCopy other = (RekapHasilSurveyCopy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.RekapHasilSurveyCopy[ id=" + id + " ]";
    }
    
}
