package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "v_dashboard_lokasi")
@Immutable
@NamedQueries({
    @NamedQuery(name = "DashboardLokasiEntity.findAll", query = "SELECT p FROM DashboardLokasiEntity p")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DashboardLokasiEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_lokasi")
    private String idLokasi;

    @Column(name = "propinsi")
    private String propinsi;

    @Column(name = "kabupaten")
    private String kabupaten;

    @Column(name = "jenis_pekerjaan")
    private String jenisPekerjaan;
    
    @Column(name = "id_laporan_mingguan")
    private String idLaporanMingguan;

    @Column(name = "rencana")
    private Float rencana;

    @Column(name = "realisasi")
    private Float realisasi;

    @Column(name = "penyerapan")
    private Float penyerapan;

    @Column(name = "status_input")
    private Date statusInput;

    @Column(name = "paket")
    private String paket;

    @Column(name = "nama_paket")
    private String namaPaket;

    /*
     * Karena di view gak ada `id`, jadi pake anotasi @Id untuk field yang kira-kira dianggap sebagai berperan sebagai
     * `id` unique.
     */
    @Id
    public String getIdLokasi() {
        return idLokasi;
    }

    public void setIdLokasi(String idLokasi) {
        this.idLokasi = idLokasi;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getJenisPekerjaan() {
        return jenisPekerjaan;
    }

    public void setJenisPekerjaan(String jenisPekerjaan) {
        this.jenisPekerjaan = jenisPekerjaan;
    }

    public float getPenyerapan() {
        if (penyerapan == null) {
            return 0;
        } else {
            return this.roundFloat(penyerapan, 2);
        }
    }

    public void setPenyerapan(Float penyerapan) {
        this.penyerapan = penyerapan;
    }

    public Date getStatusInput() {
        return statusInput;
    }

    public void setStatusInput(Date statusInput) {
        this.statusInput = statusInput;
    }

    public float getRencana() {
        if (rencana == null) {
            return 0;
        } else {
            return this.roundFloat(rencana, 2);
        }

    }

    public void setRencana(Float rencana) {
        this.rencana = rencana;
    }

    public float getRealisasi() {
        if (realisasi == null) {
            return 0;
        } else {
            return this.roundFloat(realisasi, 2);
        }
    }

    public void setRealisasi(Float realisasi) {
        this.realisasi = realisasi;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public float ambilDeviasi()
    {
        return this.roundFloat(this.getRealisasi() - this.getRencana(), 2);
    }

    /**
     * Get Background Deviasi
     * @return String
     */
    public String ambilDeviasiLokasiBackColor()
    {
        if (this.ambilDeviasi() < 0 && this.ambilDeviasi() >= -10) {
            return "bg-yellow-crusta bg-font-yellow-crusta";
        } else if (this.ambilDeviasi() < -10) {
            return "bg-red-thunderbird bg-font-red-thunderbird";
        }else if (this.ambilDeviasi() > 0) {
            return "bg-blue-soft bg-font-blue-soft";
        } else {
            return "";
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLokasi != null ? idLokasi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DashboardLokasiEntity)) {
            return false;
        }
        DashboardLokasiEntity other = (DashboardLokasiEntity) object;
        if ((this.idLokasi == null && other.idLokasi != null) || (this.idLokasi != null && !this.idLokasi.equals(other.idLokasi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.DashboardLokasiEntity[ idLokasi=" + idLokasi + " ]";
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

    /**
     * @return the idLaporanMingguan
     */
    public String getIdLaporanMingguan() {
        return idLaporanMingguan;
    }

    /**
     * @param idLaporanMingguan the idLaporanMingguan to set
     */
    public void setIdLaporanMingguan(String idLaporanMingguan) {
        this.idLaporanMingguan = idLaporanMingguan;
    }

}
