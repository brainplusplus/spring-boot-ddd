package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "v_dashboard_ppk")
@Immutable
@NamedQueries({
    @NamedQuery(name = "DashboardPpkEntity.findAll", query = "SELECT p FROM DashboardPpkEntity p")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DashboardPpkEntity {

    private static final long serialVersionUID = 1L;

    /*
     * Kind of Override? Dunno, lol
     */
    @Basic(optional = false)
    @Column(name = "id_ppk")
    private String idPpk;

    @Column(name = "nama_satuan_besar")
    private String namaSatuanBesar;

    @Column(name = "id_satker")
    private String idSatker;

    @Column(name = "nama_satker")
    private String namaSatker;

    @Column(name = "rencana")
    private Float rencana;

    @Column(name = "realisasi")
    private Float realisasi;

    @Column(name = "penyerapan")
    private Float penyerapan;

    /*
     * Karena di view gak ada `id`, jadi pake anotasi @Id untuk field yang kira-kira dianggap sebagai berperan sebagai
     * `id` unique.
     */
    @Id
    public String getIdPpk() {
        return idPpk;
    }

    public void setIdPpk(String id) {
        this.idPpk = id;
    }

    public String getNamaSatker() {
        return namaSatker;
    }

    public void setNamaSatker(String namaSatker) {
        this.namaSatker = namaSatker;
    }

    public String getNamaSatuanBesar() {
        return namaSatuanBesar;
    }

    public void setNamaSatuanBesar(String namaSatuanBesar) {
        this.namaSatuanBesar = namaSatuanBesar;
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

    public String getIdSatker() {
        return idSatker;
    }

    public void setIdSatker(String idSatker) {
        this.idSatker = idSatker;
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

    public float ambilDeviasi()
    {
        return this.roundFloat(this.getRealisasi() - this.getRencana(), 2);
    }

    /**
     * Get Background Deviasi
     * @return String
     */
    public String ambilDeviasiBackColor()
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
        hash += (idPpk != null ? idPpk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DashboardPpkEntity)) {
            return false;
        }
        DashboardPpkEntity other = (DashboardPpkEntity) object;
        if ((this.idPpk == null && other.idPpk != null) || (this.idPpk != null && !this.idPpk.equals(other.idPpk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.DashboardPpkEntity[ id=" + idPpk + " ]";
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
