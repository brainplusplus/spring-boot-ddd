/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author brainplusplus
 */
@Entity
@Table(name = "user_app")
@NamedQueries({
    @NamedQuery(name = "UserApp.findAll", query = "SELECT w FROM UserApp w")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserApp extends BaseModel {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "real_name")
    private String realName;
    @Column(name = "email")
    private String email;
    @Column(name = "telepon")
    private String telepon;
    @Column(name = "tempat_lahir")
    private String tempatLahir;
    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    @ManyToOne
    private Role role;
    @JoinColumn(name = "id_wilayah", referencedColumnName = "id")
    @ManyToOne
    private Wilayah idWilayah;
    @Basic(optional = false)
    @Column(name = "dirjen")
    private String dirjen;
    @Basic(optional = false)
    @Column(name = "jabatan")
    private String jabatan;
    @Basic(optional = false)
    @Column(name = "kode")
    private String kode;
    @Column(name = "active")
    private boolean active;
    @Column(name = "logo")
    private String logo;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<RelPpkPaket> relPpkPaketList;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<RelSatkerPpk> relSatkerPpkList;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<LokasiSurvey> lokasiSurveyList;
    
    @JsonIgnore
    @OneToMany(mappedBy = "idUser")
    private List<UserRole> userRoleList;
    
    @JsonIgnore
    @OneToMany(mappedBy = "idUser")
    private List<SessionApp> ciSessionsList;

    public UserApp() {
    }

    public UserApp(String id) {
        this.id = id;
    }

    public UserApp(String id, String dirjen, String jabatan, String kode) {
        this.id = id;
        this.dirjen = dirjen;
        this.jabatan = jabatan;
        this.kode = kode;
    }
    
        /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//    public Integer getIdWilayah() {
//        return idWilayah;
//    }
//
//    public void setIdWilayah(Integer idWilayah) {
//        this.idWilayah = idWilayah;
//    }

    public String getDirjen() {
        return dirjen;
    }

    public void setDirjen(String dirjen) {
        this.dirjen = dirjen;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<RelPpkPaket> getRelPpkPaketList() {
        return relPpkPaketList;
    }

    public void setRelPpkPaketList(List<RelPpkPaket> relPpkPaketList) {
        this.relPpkPaketList = relPpkPaketList;
    }

    public List<RelSatkerPpk> getRelSatkerPpkList() {
        return relSatkerPpkList;
    }

    public void setRelSatkerPpkList(List<RelSatkerPpk> relSatkerPpkList) {
        this.relSatkerPpkList = relSatkerPpkList;
    }

    public List<LokasiSurvey> getLokasiSurveyList() {
        return lokasiSurveyList;
    }

    public void setLokasiSurveyList(List<LokasiSurvey> lokasiSurveyList) {
        this.lokasiSurveyList = lokasiSurveyList;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public List<SessionApp> getCiSessionsList() {
        return ciSessionsList;
    }

    public void setCiSessionsList(List<SessionApp> ciSessionsList) {
        this.ciSessionsList = ciSessionsList;
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
        if (!(object instanceof UserApp)) {
            return false;
        }
        UserApp other = (UserApp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.entity.User[ id=" + id + " ]";
    }

    /**
     * @return the idWilayah
     */
    public Wilayah getIdWilayah() {
        return idWilayah;
    }

    /**
     * @param idWilayah the idWilayah to set
     */
    public void setIdWilayah(Wilayah idWilayah) {
        this.idWilayah = idWilayah;
    }
    
}
