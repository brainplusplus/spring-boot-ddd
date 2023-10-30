package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "v_dashboard_satker")
@NamedQueries({
    @NamedQuery(name = "DashboardSatkerEntity.findAll", query = "SELECT p FROM DashboardSatkerEntity p")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DashboardSatkerEntity extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "nama_satker")
    private String namaSatker;

    @Basic(optional =  false)
    @Column(name = "jumlah_satuan")
    private int jumlahSatuan;

    @Column(name = "lokasi")
    private int lokasi;

    @Column(name = "rencana")
    private float rencana;

    @Column(name = "realisasi")
    private float realisasi;

    @Column(name = "cepat")
    private int cepat;

    @Column(name = "lambat")
    private int lambat;

    public DashboardSatkerEntity() {
    }

    public DashboardSatkerEntity(String id) {
        this.id = id;
    }

    public DashboardSatkerEntity(String id, String namaSatker) {
        this.id = id;
        this.namaSatker = namaSatker;
    }

    public String getNamaSatker() {
        return namaSatker;
    }

    public void setNamaSatker(String namaSatker) {
        this.namaSatker = namaSatker;
    }

    public int getJumlahSatuan() {
        return jumlahSatuan;
    }

    public void setJumlahSatuan(int jumlahSatuan) {
        this.jumlahSatuan = jumlahSatuan;
    }

    public int getLokasi() {
        return lokasi;
    }

    public void setLokasi(int lokasi) {
        this.lokasi = lokasi;
    }

    public float getRencana() {
        return this.roundFloat(rencana, 2);
    }

    public void setRencana(float rencana) {
        this.rencana = rencana;
    }

    public float getRealisasi() {
        return this.roundFloat(realisasi, 2);
    }

    public void setRealisasi(float realisasi) {
        this.realisasi = realisasi;
    }

    public int getCepat() {
        return cepat;
    }

    public String getCepatString() {
        return cepat + " lokasi";
    }

    public void setCepat(int cepat) {
        this.cepat = cepat;
    }

    public int getLambat() {
        return lambat;
    }

    public String getLambatString()
    {
        return lambat + " lokasi";
    }

    public void setLambat(int lambat) {
        this.lambat = lambat;
    }

    /**
     * Get Deviasi
     * @return float
     */
    public float getDeviasi() {
        return this.roundFloat(this.getRealisasi() - this.getRencana(), 2);
    }

    /**
     * Get Background Deviasi
     * @return String
     */
    public String getDeviasiBackColor()
    {
        if (this.getDeviasi() < 0 && this.getDeviasi() >= -10) {
            return "bg-yellow-crusta bg-font-yellow-crusta";
        }else if (this.getDeviasi() <-10) {
            return "bg-red-thunderbird bg-font-red-thunderbird";
        } else if (this.getDeviasi() > 0) {
            return "bg-blue-soft bg-font-blue-soft ";
        }  else {
            return "";
        }
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
        if (!(object instanceof DashboardSatkerEntity)) {
            return false;
        }
        DashboardSatkerEntity other = (DashboardSatkerEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.DashboardSatkerEntity[ id=" + id + " ]";
    }

    /**
     * Round to certain number of decimals
     * See http://stackoverflow.com/a/8911683/2485734
     * @param givenFloat float
     * @param decimalPlace int
     * @return float
     */
    private float roundFloat(float givenFloat, int decimalPlace)
    {
        BigDecimal bd = new BigDecimal(Float.toString(givenFloat));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
