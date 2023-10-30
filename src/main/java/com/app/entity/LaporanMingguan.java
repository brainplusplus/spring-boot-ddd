/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import com.app.object.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "laporan_mingguan")
@NamedQueries({
    @NamedQuery(name = "LaporanMingguan.findAll", query = "SELECT l FROM LaporanMingguan l")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LaporanMingguan extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "laporan_periode_minggu_ke")
    private Integer laporanPeriodeMingguKe;
    @JsonSerialize(using = CustomDateSerializer.class)
    @Basic(optional = false)
    @Column(name = "tanggal_dari")
    @Temporal(TemporalType.DATE)
    private Date tanggalDari;
    @JsonSerialize(using = CustomDateSerializer.class)
    @Basic(optional = false)
    @Column(name = "tanggal_sampai")
    @Temporal(TemporalType.DATE)
    private Date tanggalSampai;
    @Column(name = "laporan_periode_minggu_ke_kontraktor")
    private Integer laporanPeriodeMingguKeKontraktor;
    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "tanggal_dari_kontraktor")
    @Temporal(TemporalType.DATE)
    private Date tanggalDariKontraktor;
    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "tanggal_sampai_kontraktor")
    @Temporal(TemporalType.DATE)
    private Date tanggalSampaiKontraktor;
    @Column(name = "waktu_pelaksanaan")
    private Integer waktuPelaksanaan;
    @Column(name = "sisa_waktu_pelaksanaan")
    private Integer sisaWaktuPelaksanaan;
    @Column(name = "progress_minggu_ini")
    private String progressMingguIni;
    @Column(name = "rencana_progress_minggu_ini")
    private String rencanaProgressMingguIni;
    @Column(name = "deviasi")
    private String deviasi;
    @Column(name = "pekerjaan_persiapan_rencana")
    private String pekerjaanPersiapanRencana;
    @Column(name = "pekerjaan_persiapan_realisasi")
    private String pekerjaanPersiapanRealisasi;
    
    @Column(name = "pekerjaan_bongkaran_rencana")
    private String pekerjaanBongkaranRencana;
    @Column(name = "pekerjaan_bongkaran_realisasi")
    private String pekerjaanBongkaranRealisasi;
    @Column(name = "pekerjaan_pasang_dinding_lantai_rencana")
    private String pekerjaanPasangDindingLantaiRencana;
    @Column(name = "pekerjaan_pasang_dinding_lantai_realisasi")
    private String pekerjaanPasangDindingLantaiRealisasi;
    @Column(name = "pekerjaan_kusen_pintu_jendela_penggantung_rencana")
    private String pekerjaanKusenPintuJendelaPenggantungRencana;
    @Column(name = "pekerjaan_kusen_pintu_jendela_penggantung_realisasi")
    private String pekerjaanKusenPintuJendelaPenggantungRealisasi;
    @Column(name = "pekerjaan_atap_plafound_rencana")
    private String pekerjaanAtapPlafoundRencana;
    @Column(name = "pekerjaan_atap_plafound_realisasi")
    private String pekerjaanAtapPlafoundRealisasi;
    @Column(name = "pekerjaan_listrik_air_sanitair_rencana")
    private String pekerjaanListrikAirSanitairRencana;
    @Column(name = "pekerjaan_listrik_air_sanitair_realisasi")
    private String pekerjaanListrikAirSanitairRealisasi;
    @Column(name = "pekerjaan_pengecetan_rencana")
    private String pekerjaanPengecetanRencana;
    @Column(name = "pekerjaan_pengecetan_realisasi")
    private String pekerjaanPengecetanRealisasi;
    @Column(name = "pekerjaan_garasi_pos_propost_rencana")
    private String pekerjaanGarasiPosPropostRencana;
    @Column(name = "pekerjaan_garasi_pos_propost_realisasi")
    private String pekerjaanGarasiPosPropostRealisasi;
    @Column(name = "pekerjaan_prasarana_rencana")
    private String pekerjaanPrasaranaRencana;
    @Column(name = "pekerjaan_prasarana_realisasi")
    private String pekerjaanPrasaranaRealisasi;
    @Column(name = "pekerjaan_tanah_pondasi_beton_rencana")
    private String pekerjaanTanahPondasiBetonRencana;
    @Column(name = "pekerjaan_tanah_pondasi_beton_realisasi")
    private String pekerjaanTanahPondasiBetonRealisasi;
    @Column(name = "pekerjaan_menara_rencana")
    private String pekerjaanMenaraRencana;
    @Column(name = "pekerjaan_menara_realisasi")
    private String pekerjaanMenaraRealisasi;
    @Column(name = "pekerjaan_hr_rencana")
    private String pekerjaanHrRencana;
    @Column(name = "pekerjaan_hr_realisasi")
    private String pekerjaanHrRealisasi;
    
    @Column(name = "pekerjaan_struktur_rencana")
    private String pekerjaanStrukturRencana;
    @Column(name = "pekerjaan_struktur_realisasi")
    private String pekerjaanStrukturRealisasi;
//    @Column(name = "pekerjaan_arsitektur_rencana")
//    private String pekerjaanArsitekturRencana;
//    @Column(name = "pekerjaan_arsitektur_realisasi")
//    private String pekerjaanArsitekturRealisasi;
//    @Column(name = "pekerjaan_me_rencana")
//    private String pekerjaanMeRencana;
//    @Column(name = "pekerjaan_me_realisasi")
//    private String pekerjaanMeRealisasi;
    @Lob
    @Column(name = "aktifitas_pokok_minggu_ini")
    private String aktifitasPokokMingguIni;
    @Lob
    @Column(name = "rencana_minggu_yang_akan_datang")
    private String rencanaMingguYangAkanDatang;
    @Lob
    @Column(name = "permasalahan_hambatan")
    private String permasalahanHambatan;
    @Lob
    @Column(name = "penyelesaian_masalah")
    private String penyelesaianMasalah;
    @Column(name = "video_kegiatan")
    private String videoKegiatan;
    @Lob
    @Column(name = "saran_dari_konsultan_mk_wilayah")
    private String saranDariKonsultanMkWilayah;
    @Lob
    @Column(name = "tanggapan_saran_ppk")
    private String tanggapanSaranPpk;
    @Lob
    @Column(name = "tanggapan_saran_kmp")
    private String tanggapanSaranKmp;
    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "tanggal_penginputan")
    @Temporal(TemporalType.DATE)
    private Date tanggalPenginputan;
    @Basic(optional = false)
    @Column(name = "total_serapan", nullable=true)
    private double totalSerapan;
    @Basic(optional = false)
    @Lob
    @Column(name = "tanggapan_direktur", nullable=true)
    private String tanggapanDirektur;
    @Basic(optional = false)
    @Lob
    @Column(name = "tanggapan_dirjen", nullable=true)
    private String tanggapanDirjen;
    @JoinColumn(name = "id_lokasi", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotFound(
           action = NotFoundAction.IGNORE)
    private LokasiSurvey idLokasi;// = new LokasiSurvey();
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLaporan")
    private List<LaporanMingguanMarking> laporanMingguanMarkingList;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLaporan")
    private List<LaporanMingguanTampakImages> laporanMingguanTampakImagesList;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLaporan")
    private List<LaporanMingguanImages> laporanMingguanImagesList;

    public LaporanMingguan() {
    }

    public LaporanMingguan(String id) {
        this.id = id;
    }

    public LaporanMingguan(String id, Date tanggalDari, Date tanggalSampai, double totalSerapan, String tanggapanDirektur, String tanggapanDirjen) {
        this.id = id;
        this.tanggalDari = tanggalDari;
        this.tanggalSampai = tanggalSampai;
        this.totalSerapan = totalSerapan;
        this.tanggapanDirektur = tanggapanDirektur;
        this.tanggapanDirjen = tanggapanDirjen;
    }

    public Integer getLaporanPeriodeMingguKe() {
        return laporanPeriodeMingguKe;
    }

    public void setLaporanPeriodeMingguKe(Integer laporanPeriodeMingguKe) {
        this.laporanPeriodeMingguKe = laporanPeriodeMingguKe;
    }

    public Date getTanggalDari() {
        return tanggalDari;
    }

    public void setTanggalDari(Date tanggalDari) {
        this.tanggalDari = tanggalDari;
    }

    public Date getTanggalSampai() {
        return tanggalSampai;
    }

    public void setTanggalSampai(Date tanggalSampai) {
        this.tanggalSampai = tanggalSampai;
    }

    public Integer getLaporanPeriodeMingguKeKontraktor() {
        return laporanPeriodeMingguKeKontraktor;
    }

    public void setLaporanPeriodeMingguKeKontraktor(Integer laporanPeriodeMingguKeKontraktor) {
        this.laporanPeriodeMingguKeKontraktor = laporanPeriodeMingguKeKontraktor;
    }

    public Date getTanggalDariKontraktor() {
        return tanggalDariKontraktor;
    }

    public void setTanggalDariKontraktor(Date tanggalDariKontraktor) {
        this.tanggalDariKontraktor = tanggalDariKontraktor;
    }

    public Date getTanggalSampaiKontraktor() {
        return tanggalSampaiKontraktor;
    }

    public void setTanggalSampaiKontraktor(Date tanggalSampaiKontraktor) {
        this.tanggalSampaiKontraktor = tanggalSampaiKontraktor;
    }

    public Integer getWaktuPelaksanaan() {
        return waktuPelaksanaan;
    }

    public void setWaktuPelaksanaan(Integer waktuPelaksanaan) {
        this.waktuPelaksanaan = waktuPelaksanaan;
    }

    public Integer getSisaWaktuPelaksanaan() {
        return sisaWaktuPelaksanaan;
    }

    public void setSisaWaktuPelaksanaan(Integer sisaWaktuPelaksanaan) {
        this.sisaWaktuPelaksanaan = sisaWaktuPelaksanaan;
    }

    public String getProgressMingguIni() {
        return progressMingguIni;
    }

    public void setProgressMingguIni(String progressMingguIni) {
        this.progressMingguIni = progressMingguIni;
    }

    public String getRencanaProgressMingguIni() {
        return rencanaProgressMingguIni;
    }

    public void setRencanaProgressMingguIni(String rencanaProgressMingguIni) {
        this.rencanaProgressMingguIni = rencanaProgressMingguIni;
    }

    public String getDeviasi() {
        return deviasi;
    }

    public void setDeviasi(String deviasi) {
        this.deviasi = deviasi;
    }

    public String getPekerjaanPersiapanRencana() {
        return pekerjaanPersiapanRencana;
    }

    public void setPekerjaanPersiapanRencana(String pekerjaanPersiapanRencana) {
        this.pekerjaanPersiapanRencana = pekerjaanPersiapanRencana;
    }

    public String getPekerjaanPersiapanRealisasi() {
        return pekerjaanPersiapanRealisasi;
    }

    public void setPekerjaanPersiapanRealisasi(String pekerjaanPersiapanRealisasi) {
        this.pekerjaanPersiapanRealisasi = pekerjaanPersiapanRealisasi;
    }

    public String getPekerjaanStrukturRencana() {
        return pekerjaanStrukturRencana;
    }

    public void setPekerjaanStrukturRencana(String pekerjaanStrukturRencana) {
        this.pekerjaanStrukturRencana = pekerjaanStrukturRencana;
    }

    public String getPekerjaanStrukturRealisasi() {
        return pekerjaanStrukturRealisasi;
    }

    public void setPekerjaanStrukturRealisasi(String pekerjaanStrukturRealisasi) {
        this.pekerjaanStrukturRealisasi = pekerjaanStrukturRealisasi;
   }
//
//    public String getPekerjaanArsitekturRencana() {
//        return pekerjaanArsitekturRencana;
//    }
//
//    public void setPekerjaanArsitekturRencana(String pekerjaanArsitekturRencana) {
//        this.pekerjaanArsitekturRencana = pekerjaanArsitekturRencana;
//    }
//
//    public String getPekerjaanArsitekturRealisasi() {
//        return pekerjaanArsitekturRealisasi;
//    }
//
//    public void setPekerjaanArsitekturRealisasi(String pekerjaanArsitekturRealisasi) {
//        this.pekerjaanArsitekturRealisasi = pekerjaanArsitekturRealisasi;
//    }
//
//    public String getPekerjaanMeRencana() {
//        return pekerjaanMeRencana;
//    }
//
//    public void setPekerjaanMeRencana(String pekerjaanMeRencana) {
//        this.pekerjaanMeRencana = pekerjaanMeRencana;
//    }
//
//    public String getPekerjaanMeRealisasi() {
//        return pekerjaanMeRealisasi;
//    }
//
//    public void setPekerjaanMeRealisasi(String pekerjaanMeRealisasi) {
//        this.pekerjaanMeRealisasi = pekerjaanMeRealisasi;
//    }

    public String getAktifitasPokokMingguIni() {
        return aktifitasPokokMingguIni;
    }

    public void setAktifitasPokokMingguIni(String aktifitasPokokMingguIni) {
        this.aktifitasPokokMingguIni = aktifitasPokokMingguIni;
    }

    public String getRencanaMingguYangAkanDatang() {
        return rencanaMingguYangAkanDatang;
    }

    public void setRencanaMingguYangAkanDatang(String rencanaMingguYangAkanDatang) {
        this.rencanaMingguYangAkanDatang = rencanaMingguYangAkanDatang;
    }

    public String getPermasalahanHambatan() {
        return permasalahanHambatan;
    }

    public void setPermasalahanHambatan(String permasalahanHambatan) {
        this.permasalahanHambatan = permasalahanHambatan;
    }

    public String getPenyelesaianMasalah() {
        return penyelesaianMasalah;
    }

    public void setPenyelesaianMasalah(String penyelesaianMasalah) {
        this.penyelesaianMasalah = penyelesaianMasalah;
    }

    public String getVideoKegiatan() {
        return videoKegiatan;
    }

    public void setVideoKegiatan(String videoKegiatan) {
        this.videoKegiatan = videoKegiatan;
    }

    public String getSaranDariKonsultanMkWilayah() {
        return saranDariKonsultanMkWilayah;
    }

    public void setSaranDariKonsultanMkWilayah(String saranDariKonsultanMkWilayah) {
        this.saranDariKonsultanMkWilayah = saranDariKonsultanMkWilayah;
    }

    public String getTanggapanSaranPpk() {
        return tanggapanSaranPpk;
    }

    public void setTanggapanSaranPpk(String tanggapanSaranPpk) {
        this.tanggapanSaranPpk = tanggapanSaranPpk;
    }

    public String getTanggapanSaranKmp() {
        return tanggapanSaranKmp;
    }

    public void setTanggapanSaranKmp(String tanggapanSaranKmp) {
        this.tanggapanSaranKmp = tanggapanSaranKmp;
    }

    public Date getTanggalPenginputan() {
        return tanggalPenginputan;
    }

    public void setTanggalPenginputan(Date tanggalPenginputan) {
        this.tanggalPenginputan = tanggalPenginputan;
    }

    public double getTotalSerapan() {
        return totalSerapan;
    }

    public void setTotalSerapan(double totalSerapan) {
        this.totalSerapan = totalSerapan;
    }

    public String getTanggapanDirektur() {
        return tanggapanDirektur;
    }

    public void setTanggapanDirektur(String tanggapanDirektur) {
        this.tanggapanDirektur = tanggapanDirektur;
    }

    public String getTanggapanDirjen() {
        return tanggapanDirjen;
    }

    public void setTanggapanDirjen(String tanggapanDirjen) {
        this.tanggapanDirjen = tanggapanDirjen;
    }

    public LokasiSurvey getIdLokasi() {
        return idLokasi;
    }

    public void setIdLokasi(LokasiSurvey idLokasi) {
        this.idLokasi = idLokasi;
    }
    
    public List<LaporanMingguanMarking> getLaporanMingguanMarkingList() {
        return laporanMingguanMarkingList;
    }

    public void setLaporanMingguanMarkingList(List<LaporanMingguanMarking> laporanMingguanMarkingList) {
        this.laporanMingguanMarkingList = laporanMingguanMarkingList;
    }

    public List<LaporanMingguanTampakImages> getLaporanMingguanTampakImagesList() {
        return laporanMingguanTampakImagesList;
    }

    public void setLaporanMingguanTampakImagesList(List<LaporanMingguanTampakImages> laporanMingguanTampakImagesList) {
        this.laporanMingguanTampakImagesList = laporanMingguanTampakImagesList;
    }

    public List<LaporanMingguanImages> getLaporanMingguanImagesList() {
        return laporanMingguanImagesList;
    }

    public void setLaporanMingguanImagesList(List<LaporanMingguanImages> laporanMingguanImagesList) {
        this.laporanMingguanImagesList = laporanMingguanImagesList;
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
        if (!(object instanceof LaporanMingguan)) {
            return false;
        }
        LaporanMingguan other = (LaporanMingguan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.LaporanMingguan[ id=" + id + " ]";
    }

    /**
     * @return the pekerjaanBongkaranRencana
     */
    public String getPekerjaanBongkaranRencana() {
        return pekerjaanBongkaranRencana;
    }

    /**
     * @param pekerjaanBongkaranRencana the pekerjaanBongkaranRencana to set
     */
    public void setPekerjaanBongkaranRencana(String pekerjaanBongkaranRencana) {
        this.pekerjaanBongkaranRencana = pekerjaanBongkaranRencana;
    }

    /**
     * @return the pekerjaanBongkaranRealisasi
     */
    public String getPekerjaanBongkaranRealisasi() {
        return pekerjaanBongkaranRealisasi;
    }

    /**
     * @param pekerjaanBongkaranRealisasi the pekerjaanBongkaranRealisasi to set
     */
    public void setPekerjaanBongkaranRealisasi(String pekerjaanBongkaranRealisasi) {
        this.pekerjaanBongkaranRealisasi = pekerjaanBongkaranRealisasi;
    }

    /**
     * @return the pekerjaanPasangDindingLantaiRencana
     */
    public String getPekerjaanPasangDindingLantaiRencana() {
        return pekerjaanPasangDindingLantaiRencana;
    }

    /**
     * @param pekerjaanPasangDindingLantaiRencana the pekerjaanPasangDindingLantaiRencana to set
     */
    public void setPekerjaanPasangDindingLantaiRencana(String pekerjaanPasangDindingLantaiRencana) {
        this.pekerjaanPasangDindingLantaiRencana = pekerjaanPasangDindingLantaiRencana;
    }

    /**
     * @return the pekerjaanPasangDindingLantaiRealisasi
     */
    public String getPekerjaanPasangDindingLantaiRealisasi() {
        return pekerjaanPasangDindingLantaiRealisasi;
    }

    /**
     * @param pekerjaanPasangDindingLantaiRealisasi the pekerjaanPasangDindingLantaiRealisasi to set
     */
    public void setPekerjaanPasangDindingLantaiRealisasi(String pekerjaanPasangDindingLantaiRealisasi) {
        this.pekerjaanPasangDindingLantaiRealisasi = pekerjaanPasangDindingLantaiRealisasi;
    }

    /**
     * @return the pekerjaanKusenPintuJendelaPenggantungRencana
     */
    public String getPekerjaanKusenPintuJendelaPenggantungRencana() {
        return pekerjaanKusenPintuJendelaPenggantungRencana;
    }

    /**
     * @param pekerjaanKusenPintuJendelaPenggantungRencana the pekerjaanKusenPintuJendelaPenggantungRencana to set
     */
    public void setPekerjaanKusenPintuJendelaPenggantungRencana(String pekerjaanKusenPintuJendelaPenggantungRencana) {
        this.pekerjaanKusenPintuJendelaPenggantungRencana = pekerjaanKusenPintuJendelaPenggantungRencana;
    }

    /**
     * @return the pekerjaanKusenPintuJendelaPenggantungRealisasi
     */
    public String getPekerjaanKusenPintuJendelaPenggantungRealisasi() {
        return pekerjaanKusenPintuJendelaPenggantungRealisasi;
    }

    /**
     * @param pekerjaanKusenPintuJendelaPenggantungRealisasi the pekerjaanKusenPintuJendelaPenggantungRealisasi to set
     */
    public void setPekerjaanKusenPintuJendelaPenggantungRealisasi(String pekerjaanKusenPintuJendelaPenggantungRealisasi) {
        this.pekerjaanKusenPintuJendelaPenggantungRealisasi = pekerjaanKusenPintuJendelaPenggantungRealisasi;
    }

    /**
     * @return the pekerjaanAtapPlafoundRencana
     */
    public String getPekerjaanAtapPlafoundRencana() {
        return pekerjaanAtapPlafoundRencana;
    }

    /**
     * @param pekerjaanAtapPlafoundRencana the pekerjaanAtapPlafoundRencana to set
     */
    public void setPekerjaanAtapPlafoundRencana(String pekerjaanAtapPlafoundRencana) {
        this.pekerjaanAtapPlafoundRencana = pekerjaanAtapPlafoundRencana;
    }

    /**
     * @return the pekerjaanAtapPlafoundRealisasi
     */
    public String getPekerjaanAtapPlafoundRealisasi() {
        return pekerjaanAtapPlafoundRealisasi;
    }

    /**
     * @param pekerjaanAtapPlafoundRealisasi the pekerjaanAtapPlafoundRealisasi to set
     */
    public void setPekerjaanAtapPlafoundRealisasi(String pekerjaanAtapPlafoundRealisasi) {
        this.pekerjaanAtapPlafoundRealisasi = pekerjaanAtapPlafoundRealisasi;
    }

    /**
     * @return the pekerjaanListrikAirSanitairRencana
     */
    public String getPekerjaanListrikAirSanitairRencana() {
        return pekerjaanListrikAirSanitairRencana;
    }

    /**
     * @param pekerjaanListrikAirSanitairRencana the pekerjaanListrikAirSanitairRencana to set
     */
    public void setPekerjaanListrikAirSanitairRencana(String pekerjaanListrikAirSanitairRencana) {
        this.pekerjaanListrikAirSanitairRencana = pekerjaanListrikAirSanitairRencana;
    }

    /**
     * @return the pekerjaanListrikAirSanitairRealisasi
     */
    public String getPekerjaanListrikAirSanitairRealisasi() {
        return pekerjaanListrikAirSanitairRealisasi;
    }

    /**
     * @param pekerjaanListrikAirSanitairRealisasi the pekerjaanListrikAirSanitairRealisasi to set
     */
    public void setPekerjaanListrikAirSanitairRealisasi(String pekerjaanListrikAirSanitairRealisasi) {
        this.pekerjaanListrikAirSanitairRealisasi = pekerjaanListrikAirSanitairRealisasi;
    }

    /**
     * @return the pekerjaanPengecetanRencana
     */
    public String getPekerjaanPengecetanRencana() {
        return pekerjaanPengecetanRencana;
    }

    /**
     * @param pekerjaanPengecetanRencana the pekerjaanPengecetanRencana to set
     */
    public void setPekerjaanPengecetanRencana(String pekerjaanPengecetanRencana) {
        this.pekerjaanPengecetanRencana = pekerjaanPengecetanRencana;
    }

    /**
     * @return the pekerjaanPengecetanRealisasi
     */
    public String getPekerjaanPengecetanRealisasi() {
        return pekerjaanPengecetanRealisasi;
    }

    /**
     * @param pekerjaanPengecetanRealisasi the pekerjaanPengecetanRealisasi to set
     */
    public void setPekerjaanPengecetanRealisasi(String pekerjaanPengecetanRealisasi) {
        this.pekerjaanPengecetanRealisasi = pekerjaanPengecetanRealisasi;
    }

    /**
     * @return the pekerjaanGarasiPosPropostRencana
     */
    public String getPekerjaanGarasiPosPropostRencana() {
        return pekerjaanGarasiPosPropostRencana;
    }

    /**
     * @param pekerjaanGarasiPosPropostRencana the pekerjaanGarasiPosPropostRencana to set
     */
    public void setPekerjaanGarasiPosPropostRencana(String pekerjaanGarasiPosPropostRencana) {
        this.pekerjaanGarasiPosPropostRencana = pekerjaanGarasiPosPropostRencana;
    }

    /**
     * @return the pekerjaanGarasiPosPropostRealisasi
     */
    public String getPekerjaanGarasiPosPropostRealisasi() {
        return pekerjaanGarasiPosPropostRealisasi;
    }

    /**
     * @param pekerjaanGarasiPosPropostRealisasi the pekerjaanGarasiPosPropostRealisasi to set
     */
    public void setPekerjaanGarasiPosPropostRealisasi(String pekerjaanGarasiPosPropostRealisasi) {
        this.pekerjaanGarasiPosPropostRealisasi = pekerjaanGarasiPosPropostRealisasi;
    }

    /**
     * @return the pekerjaanPrasaranaRencana
     */
    public String getPekerjaanPrasaranaRencana() {
        return pekerjaanPrasaranaRencana;
    }

    /**
     * @param pekerjaanPrasaranaRencana the pekerjaanPrasaranaRencana to set
     */
    public void setPekerjaanPrasaranaRencana(String pekerjaanPrasaranaRencana) {
        this.pekerjaanPrasaranaRencana = pekerjaanPrasaranaRencana;
    }

    /**
     * @return the pekerjaanPrasaranaRealisasi
     */
    public String getPekerjaanPrasaranaRealisasi() {
        return pekerjaanPrasaranaRealisasi;
    }

    /**
     * @param pekerjaanPrasaranaRealisasi the pekerjaanPrasaranaRealisasi to set
     */
    public void setPekerjaanPrasaranaRealisasi(String pekerjaanPrasaranaRealisasi) {
        this.pekerjaanPrasaranaRealisasi = pekerjaanPrasaranaRealisasi;
    }

    /**
     * @return the pekerjaanTanahPondasiBetonRencana
     */
    public String getPekerjaanTanahPondasiBetonRencana() {
        return pekerjaanTanahPondasiBetonRencana;
    }

    /**
     * @param pekerjaanTanahPondasiBetonRencana the pekerjaanTanahPondasiBetonRencana to set
     */
    public void setPekerjaanTanahPondasiBetonRencana(String pekerjaanTanahPondasiBetonRencana) {
        this.pekerjaanTanahPondasiBetonRencana = pekerjaanTanahPondasiBetonRencana;
    }

    /**
     * @return the pekerjaanTanahPondasiBetonRealisasi
     */
    public String getPekerjaanTanahPondasiBetonRealisasi() {
        return pekerjaanTanahPondasiBetonRealisasi;
    }

    /**
     * @param pekerjaanTanahPondasiBetonRealisasi the pekerjaanTanahPondasiBetonRealisasi to set
     */
    public void setPekerjaanTanahPondasiBetonRealisasi(String pekerjaanTanahPondasiBetonRealisasi) {
        this.pekerjaanTanahPondasiBetonRealisasi = pekerjaanTanahPondasiBetonRealisasi;
    }

    /**
     * @return the pekerjaanMenaraRencana
     */
    public String getPekerjaanMenaraRencana() {
        return pekerjaanMenaraRencana;
    }

    /**
     * @param pekerjaanMenaraRencana the pekerjaanMenaraRencana to set
     */
    public void setPekerjaanMenaraRencana(String pekerjaanMenaraRencana) {
        this.pekerjaanMenaraRencana = pekerjaanMenaraRencana;
    }

    /**
     * @return the pekerjaanMenaraRealisasi
     */
    public String getPekerjaanMenaraRealisasi() {
        return pekerjaanMenaraRealisasi;
    }

    /**
     * @param pekerjaanMenaraRealisasi the pekerjaanMenaraRealisasi to set
     */
    public void setPekerjaanMenaraRealisasi(String pekerjaanMenaraRealisasi) {
        this.pekerjaanMenaraRealisasi = pekerjaanMenaraRealisasi;
    }

    /**
     * @return the pekerjaanHrRencana
     */
    public String getPekerjaanHrRencana() {
        return pekerjaanHrRencana;
    }

    /**
     * @param pekerjaanHrRencana the pekerjaanHrRencana to set
     */
    public void setPekerjaanHrRencana(String pekerjaanHrRencana) {
        this.pekerjaanHrRencana = pekerjaanHrRencana;
    }

    /**
     * @return the pekerjaanHrRealisasi
     */
    public String getPekerjaanHrRealisasi() {
        return pekerjaanHrRealisasi;
    }

    /**
     * @param pekerjaanHrRealisasi the pekerjaanHrRealisasi to set
     */
    public void setPekerjaanHrRealisasi(String pekerjaanHrRealisasi) {
        this.pekerjaanHrRealisasi = pekerjaanHrRealisasi;
    }
    
}
