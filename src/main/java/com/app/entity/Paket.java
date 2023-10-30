package com.app.entity;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "paket")
@NamedQueries({
    @NamedQuery(name = "Paket.findAll", query = "SELECT p FROM Paket p")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Paket extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "kode_paket")
    private String kodePaket;
    @Column(name = "nama_paket")
    private String namaPaket;
    @Lob
    @Column(name = "deskripsi")
    private String deskripsi;
    @Column(name = "tahun")
    private String tahun;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaket")
    private List<RelPpkPaket> relPpkPaketList;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaket")
    private List<RelWilayahProv> relWilayahProvList;
    
    @JsonIgnore
    @OneToMany(mappedBy = "idPaket")
    private List<SerapanRencana> serapanRencanaList;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaket")
    private List<LokasiSurvey> lokasiSurveyList;

    public Paket() {
    }

    public Paket(String id) {
        this.id = id;
    }

    public Paket(String id, String kodePaket, String deskripsiPaket) {
        this.id = id;
        this.kodePaket = kodePaket;
        this.deskripsi = deskripsiPaket;
    }
    
    public String getKodePaket() {
        return kodePaket;
    }

    public void setKodePaket(String kodePaket) {
        this.kodePaket = kodePaket;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public List<RelPpkPaket> getRelPpkPaketList() {
        return relPpkPaketList;
    }

    public void setRelPpkPaketList(List<RelPpkPaket> relPpkPaketList) {
        this.relPpkPaketList = relPpkPaketList;
    }

    public List<RelWilayahProv> getRelWilayahProvList() {
        return relWilayahProvList;
    }

    public void setRelWilayahProvList(List<RelWilayahProv> relWilayahProvList) {
        this.relWilayahProvList = relWilayahProvList;
    }

    public List<SerapanRencana> getSerapanRencanaList() {
        return serapanRencanaList;
    }

    public void setSerapanRencanaList(List<SerapanRencana> serapanRencanaList) {
        this.serapanRencanaList = serapanRencanaList;
    }

    public List<LokasiSurvey> getLokasiSurveyList() {
        return lokasiSurveyList;
    }

    public void setLokasiSurveyList(List<LokasiSurvey> lokasiSurveyList) {
        this.lokasiSurveyList = lokasiSurveyList;
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
        if (!(object instanceof Paket)) {
            return false;
        }
        Paket other = (Paket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.Paket[ id=" + id + " ]";
    }

    /**
     * @return the tahun
     */
    public String getTahun() {
        return tahun;
    }

    /**
     * @param tahun the tahun to set
     */
    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
    
}
