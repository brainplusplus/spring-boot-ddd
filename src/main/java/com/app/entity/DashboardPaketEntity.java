package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "v_dashboard_paket")
@Immutable
@NamedQueries({
    @NamedQuery(name = "DashboardPaketEntity.findAll", query = "SELECT p FROM DashboardPaketEntity p")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DashboardPaketEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "kode_paket")
    private String kodePaket;

    @Column(name = "nama_paket")
    private String namaPaket;

    @Column(name = "total_lokasi")
    private int totalLokasi;

    @Column(name = "id_paket")
    private String idPaket;

    @Column(name = "id_user")
    private String idUser;

    @Column(name = "nama_ppk")
    private String namaPpk;

    @Column(name = "rencana")
    private Float rencana;

    @Column(name = "realisasi")
    private Float realisasi;

    @Column(name = "penyerapan")
    private Float penyerapan;

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

    public int getTotalLokasi() {
        return totalLokasi;
    }

    public void setTotalLokasi(int totalLokasi) {
        this.totalLokasi = totalLokasi;
    }

    /*
     * Karena di view gak ada `id`, jadi pake anotasi @Id untuk field yang kira-kira dianggap sebagai berperan sebagai
     * `id` unique.
     */
    @Id
    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaPpk() {
        return namaPpk;
    }

    public void setNamaPpk(String namaPpk) {
        this.namaPpk = namaPpk;
    }

    public float ambilDeviasi()
    {
        return this.roundFloat(this.getRealisasi() - this.getRencana(), 2);
    }

    /**
     * Get Background Deviasi
     * @return String
     */
    public String ambilDeviasiPaketBackColor()
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
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DashboardPaketEntity)) {
            return false;
        }
        DashboardPaketEntity other = (DashboardPaketEntity) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.kostrad.entity.DashboardPaketEntity[ idUser=" + idUser + " ]";
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
