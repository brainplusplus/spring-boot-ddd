/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import com.app.object.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "lokasi_survey")
@NamedQueries({
    @NamedQuery(name = "LokasiSurvey.findAll", query = "SELECT l FROM LokasiSurvey l")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LokasiSurvey extends BaseModel {

    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name = "kode_prov", referencedColumnName = "kode")
    @ManyToOne(optional = false)
    @NotFound(
            action = NotFoundAction.IGNORE)
    private Prov kodeProv;

    @JoinColumn(name = "kode_kab", referencedColumnName = "kode_kab")
    @ManyToOne(optional = false)
    @NotFound(
            action = NotFoundAction.IGNORE)
    private Kab kodeKab;
    
    @Column(name = "nama_lokasi")
    private String namaLokasi;
    @Column(name = "kategori_kegiatan")
    private String kategoriKegiatan;
    @Column(name = "nama_surveyor")
    private String namaSurveyor;
    @Column(name = "nama_konsultan_mk")
    private String namaKonsultanMk;
    @Column(name = "team_leader")
    private String teamLeader;
    @Column(name = "co_leader")
    private String coLeader;
    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "tanggal_survey")
    @Temporal(TemporalType.DATE)
    private Date tanggalSurvey;
    @Column(name = "kontraktor_pelaksana")
    private String kontraktorPelaksana;
    @Column(name = "pm")
    private String pm;
    @Column(name = "sm")
    private String sm;
    @Column(name = "lat_deg")
    private String latDeg;
    @Column(name = "lat_min")
    private String latMin;
    @Column(name = "lat_sec")
    private String latSec;
    @Column(name = "lat_dir")
    private String latDir;
    @Column(name = "lattitude")
    private String lattitude;
    @Column(name = "long_deg")
    private String longDeg;
    @Column(name = "long_min")
    private String longMin;
    @Column(name = "long_sec")
    private String longSec;
    @Column(name = "long_dir")
    private String longDir;
    @Column(name = "longitude")
    private String longitude;
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
    @Column(name = "dimensi_lantai")
    private String dimensiLantai;
    @Column(name = "peruntukan")
    private String peruntukan;
    @Column(name = "unit")
    private String unit;
    @Column(name = "tower")
    private String tower;
    @Column(name = "lokasi")
    private String lokasi;
    @Column(name = "hps")
    private String hps;
    @Column(name = "no_paket_fisik")
    private String noPaketFisik;
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
    @Lob
    @Column(name = "keterangan")
    private String keterangan;
    @Lob
    @Column(name = "rekomendasi")
    private String rekomendasi;
    @Column(name = "no_kontrak")
    private String noKontrak;
    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "tanggal_kontrak")
    @Temporal(TemporalType.DATE)
    private Date tanggalKontrak;
    @Column(name = "nilai_kontrak")
    private String nilaiKontrak;
    @Column(name = "amandemen_kontrak_1")
    private String amandemenKontrak1;
    @Column(name = "nilai_addendum_1")
    private String nilaiAddendum1;
    @Column(name = "lembaga_penerima")
    private String lembagaPenerima;
    @Column(name = "type_bangunan")
    private String typeBangunan;
    @Column(name = "jumlah_lantai")
    private Integer jumlahLantai;
    @Column(name = "jangka_waktu_pelaksanaan")
    private Integer jangkaWaktuPelaksanaan;
    @Column(name = "pekerjaan_persiapan")
    private String pekerjaanPersiapan;
    @Basic(optional = false)
    @Column(name = "kurva_s_rencana")
    private String kurvaSRencana;
    @Column(name = "pekerjaan_struktur")
    private String pekerjaanStruktur;
    @Column(name = "pekerjaan_arsitektur")
    private String pekerjaanArsitektur;
    @Column(name = "pekerjaan_mep")
    private String pekerjaanMep;
    @Basic(optional = false)
    @Column(name = "pengawas_lapangan")
    private String pengawasLapangan;
    @Basic(optional = false)
    @Column(name = "telepon_pengawas_lapangan")
    private String teleponPengawasLapangan;
    @Basic(optional = false)
    @Column(name = "telepon_team_leader")
    private String teleponTeamLeader;
    @Basic(optional = false)
    @Column(name = "telepon_co_leader")
    private String teleponCoLeader;
    @Basic(optional = false)
    @Column(name = "telepon_pm")
    private String teleponPm;
    @Basic(optional = false)
    @Column(name = "telepon_sm")
    private String teleponSm;
    @Basic(optional = false)
    @Column(name = "gambar_rencana")
    private String gambarRencana;
    @Column(name = "tahun")
    private String tahun;
    @Column(name = "tanggal_akhir_kontrak")
    @Temporal(TemporalType.DATE)
    private Date tanggalAkhirKontrak;
    @Column(name = "no_spmk")
    private String noSpmk;
    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "tanggal_spmk")
    @Temporal(TemporalType.DATE)
    private Date tanggalSpmk;
    @Column(name = "id_user_pengawas")
    private String idUserPengawas;
    @Column(name = "id_user_rekanan")
    private String idUserRekanan;
    @JoinColumn(name = "id_paket", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paket idPaket;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotFound(
            action = NotFoundAction.IGNORE)
    private UserApp idUser;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLokasi")
    private List<LokasiImages> lokasiImagesList;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLokasi")
    private List<LaporanMingguan> laporanMingguanList = new ArrayList<LaporanMingguan>();

    public LokasiSurvey() {
    }

    public LokasiSurvey(String id) {
        this.id = id;
    }

    public LokasiSurvey(String id, String kurvaSRencana, String pengawasLapangan, String teleponPengawasLapangan, String teleponTeamLeader, String teleponCoLeader, String teleponPm, String teleponSm, String gambarRencana) {
        this.id = id;
        this.kurvaSRencana = kurvaSRencana;
        this.pengawasLapangan = pengawasLapangan;
        this.teleponPengawasLapangan = teleponPengawasLapangan;
        this.teleponTeamLeader = teleponTeamLeader;
        this.teleponCoLeader = teleponCoLeader;
        this.teleponPm = teleponPm;
        this.teleponSm = teleponSm;
        this.gambarRencana = gambarRencana;
    }

    public Prov getKodeProv() {
        return kodeProv;
    }

    public void setKodeProv(Prov kodeProv) {
        this.kodeProv = kodeProv;
    }

    public Kab getKodeKab() {
        return kodeKab;
    }

    public void setKodeKab(Kab kodeKab) {
        this.kodeKab = kodeKab;
    }

    public String getNamaLokasi() {
        return namaLokasi;
    }

    public void setNamaLokasi(String namaLokasi) {
        this.namaLokasi = namaLokasi;
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

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getCoLeader() {
        return coLeader;
    }

    public void setCoLeader(String coLeader) {
        this.coLeader = coLeader;
    }

    public Date getTanggalSurvey() {
        return tanggalSurvey;
    }

    public void setTanggalSurvey(Date tanggalSurvey) {
        this.tanggalSurvey = tanggalSurvey;
    }

    public String getKontraktorPelaksana() {
        return kontraktorPelaksana;
    }

    public void setKontraktorPelaksana(String kontraktorPelaksana) {
        this.kontraktorPelaksana = kontraktorPelaksana;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
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

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getDimensiLantai() {
        return dimensiLantai;
    }

    public void setDimensiLantai(String dimensiLantai) {
        this.dimensiLantai = dimensiLantai;
    }

    public String getPeruntukan() {
        return peruntukan;
    }

    public void setPeruntukan(String peruntukan) {
        this.peruntukan = peruntukan;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getHps() {
        return hps;
    }

    public void setHps(String hps) {
        this.hps = hps;
    }

    public String getNoPaketFisik() {
        return noPaketFisik;
    }

    public void setNoPaketFisik(String noPaketFisik) {
        this.noPaketFisik = noPaketFisik;
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

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public Date getTanggalKontrak() {
        return tanggalKontrak;
    }

    public void setTanggalKontrak(Date tanggalKontrak) {
        this.tanggalKontrak = tanggalKontrak;
    }

    public String getNilaiKontrak() {
        return nilaiKontrak;
    }

    public void setNilaiKontrak(String nilaiKontrak) {
        this.nilaiKontrak = nilaiKontrak;
    }

    public String getAmandemenKontrak1() {
        return amandemenKontrak1;
    }

    public void setAmandemenKontrak1(String amandemenKontrak1) {
        this.amandemenKontrak1 = amandemenKontrak1;
    }

    public String getNilaiAddendum1() {
        return nilaiAddendum1;
    }

    public void setNilaiAddendum1(String nilaiAddendum1) {
        this.nilaiAddendum1 = nilaiAddendum1;
    }

    public String getLembagaPenerima() {
        return lembagaPenerima;
    }

    public void setLembagaPenerima(String lembagaPenerima) {
        this.lembagaPenerima = lembagaPenerima;
    }

    public String getTypeBangunan() {
        return typeBangunan;
    }

    public void setTypeBangunan(String typeBangunan) {
        this.typeBangunan = typeBangunan;
    }

    public Integer getJumlahLantai() {
        return jumlahLantai;
    }

    public void setJumlahLantai(Integer jumlahLantai) {
        this.jumlahLantai = jumlahLantai;
    }

    public Integer getJangkaWaktuPelaksanaan() {
        return jangkaWaktuPelaksanaan;
    }

    public void setJangkaWaktuPelaksanaan(Integer jangkaWaktuPelaksanaan) {
        this.jangkaWaktuPelaksanaan = jangkaWaktuPelaksanaan;
    }

    public String getPekerjaanPersiapan() {
        return pekerjaanPersiapan;
    }

    public void setPekerjaanPersiapan(String pekerjaanPersiapan) {
        this.pekerjaanPersiapan = pekerjaanPersiapan;
    }

    public String getKurvaSRencana() {
        return kurvaSRencana;
    }

    public void setKurvaSRencana(String kurvaSRencana) {
        this.kurvaSRencana = kurvaSRencana;
    }

    public String getPekerjaanStruktur() {
        return pekerjaanStruktur;
    }

    public void setPekerjaanStruktur(String pekerjaanStruktur) {
        this.pekerjaanStruktur = pekerjaanStruktur;
    }

    public String getPekerjaanArsitektur() {
        return pekerjaanArsitektur;
    }

    public void setPekerjaanArsitektur(String pekerjaanArsitektur) {
        this.pekerjaanArsitektur = pekerjaanArsitektur;
    }

    public String getPekerjaanMep() {
        return pekerjaanMep;
    }

    public void setPekerjaanMep(String pekerjaanMep) {
        this.pekerjaanMep = pekerjaanMep;
    }

    public String getPengawasLapangan() {
        return pengawasLapangan;
    }

    public void setPengawasLapangan(String pengawasLapangan) {
        this.pengawasLapangan = pengawasLapangan;
    }

    public String getTeleponPengawasLapangan() {
        return teleponPengawasLapangan;
    }

    public void setTeleponPengawasLapangan(String teleponPengawasLapangan) {
        this.teleponPengawasLapangan = teleponPengawasLapangan;
    }

    public String getTeleponTeamLeader() {
        return teleponTeamLeader;
    }

    public void setTeleponTeamLeader(String teleponTeamLeader) {
        this.teleponTeamLeader = teleponTeamLeader;
    }

    public String getTeleponCoLeader() {
        return teleponCoLeader;
    }

    public void setTeleponCoLeader(String teleponCoLeader) {
        this.teleponCoLeader = teleponCoLeader;
    }

    public String getTeleponPm() {
        return teleponPm;
    }

    public void setTeleponPm(String teleponPm) {
        this.teleponPm = teleponPm;
    }

    public String getTeleponSm() {
        return teleponSm;
    }

    public void setTeleponSm(String teleponSm) {
        this.teleponSm = teleponSm;
    }

    public String getGambarRencana() {
        return gambarRencana;
    }

    public void setGambarRencana(String gambarRencana) {
        this.gambarRencana = gambarRencana;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public Paket getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(Paket idPaket) {
        this.idPaket = idPaket;
    }

    public UserApp getIdUser() {
        return idUser;
    }

    public void setIdUser(UserApp idUser) {
        this.idUser = idUser;
    }

    public List<LokasiImages> getLokasiImagesList() {
        return lokasiImagesList;
    }

    public void setLokasiImagesList(List<LokasiImages> lokasiImagesList) {
        this.lokasiImagesList = lokasiImagesList;
    }

    public List<LaporanMingguan> getLaporanMingguanList() {
        return laporanMingguanList;
    }

    public void setLaporanMingguanList(List<LaporanMingguan> laporanMingguanList) {
        this.laporanMingguanList = laporanMingguanList;
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
        if (!(object instanceof LokasiSurvey)) {
            return false;
        }
        LokasiSurvey other = (LokasiSurvey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.LokasiSurvey[ id=" + id + " ]";
    }
    
    public String getKategoriKegiatan() {
        return kategoriKegiatan;
    }
    
    public void setKategoriKegiatan(String kategoriKegiatan) {
        this.kategoriKegiatan = kategoriKegiatan;
    }
  
    public Date getTanggalAkhirKontrak() {
        return tanggalAkhirKontrak;
    }

    public void setTanggalAkhirKontrak(Date tanggalAkhirKontrak) {
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    public String getNoSpmk() {
        return noSpmk;
    }
 
    public void setNoSpmk(String noSpmk) {
        this.noSpmk = noSpmk;
    }

    public Date getTanggalSpmk() {
        return tanggalSpmk;
    }

    public void setTanggalSpmk(Date tanggalSpmk) {
        this.tanggalSpmk = tanggalSpmk;
    }

    /**
     * @return the idUserPengawas
     */
    public String getIdUserPengawas() {
        return idUserPengawas;
    }

    /**
     * @param idUserPengawas the idUserPengawas to set
     */
    public void setIdUserPengawas(String idUserPengawas) {
        this.idUserPengawas = idUserPengawas;
    }

    /**
     * @return the idUserRekanan
     */
    public String getIdUserRekanan() {
        return idUserRekanan;
    }

    /**
     * @param idUserRekanan the idUserRekanan to set
     */
    public void setIdUserRekanan(String idUserRekanan) {
        this.idUserRekanan = idUserRekanan;
    }
    
}
