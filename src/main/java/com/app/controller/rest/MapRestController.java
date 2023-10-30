/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller.rest;

import com.app.dao.KabupatenDao;
import com.app.dao.LaporanMingguanDao;
import com.app.dao.LaporanMingguanImagesDao;
import com.app.dao.ProvinsiDao;
import com.app.dao.RelPpkPaketDao;
import com.app.entity.Kab;
import com.app.entity.LaporanMingguan;
import com.app.entity.LaporanMingguanImages;
import com.app.entity.LokasiImages;
import com.app.entity.LokasiSurvey;
import com.app.entity.Paket;
import com.app.entity.Prov;
import com.app.entity.RelPpkPaket;
import com.app.entity.UserApp;
import com.app.object.ResponseObject;
import com.app.service.master.GambarLokasiService;
import com.app.service.master.LokasiService;
import com.app.service.master.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dimasrh <ryan.hawari at gmail.com>
 */
@RestController
@RequestMapping("/rest/map")
public class MapRestController {

    @Autowired
    LokasiService ls;

    @Autowired
    KabupatenDao kd;

    @Autowired
    ProvinsiDao pd;

    @Autowired
    RelPpkPaketDao rppd;

    @Autowired
    private UserService userService;

    @Autowired
    GambarLokasiService gls;

    @Autowired
    LaporanMingguanDao lmd;

    @Autowired
    LaporanMingguanImagesDao lmid;

    @RequestMapping(value = "/propinsi_marker", method = RequestMethod.POST)
    public ResponseObject propinsiMarker() {
        ResponseObject response = new ResponseObject();
        try {
            Iterable<Prov> propinsi = pd.findAll();
            response.data = propinsi;
            response.isSuccess = true;
            response.message = "Data Ditemukan";
        } catch (Exception ex) {
            response.message = ex.getMessage();
            response.isSuccess = false;
        }
        return response;
    }

    @RequestMapping(value = "/get_propinsi", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject getPropinsi(
            HttpServletRequest request,
            @RequestParam(value = "kode_prov", required = true) String kodeProv) {
        ResponseObject<Object> response = new ResponseObject<Object>();
        try {
            Prov propinsi = pd.findByKode(kodeProv);
            HashMap data = new HashMap();
            data.put("x_coord", propinsi.getXCoord());
            data.put("y_coord", propinsi.getYCoord());
            response.data = data;
        } catch (Exception ex) {
            response.message = ex.getMessage();
            response.isSuccess = false;
        }
        return response;
    }

    @RequestMapping(value = "/center_kabupaten/{kode_kab:.+}", method = RequestMethod.GET)
    public ResponseObject centerKabupaten(@PathVariable("kode_kab") String kodeKab) {
        ResponseObject<Kab> response = new ResponseObject<Kab>();
        try {
            Kab kabupaten = kd.findByKode(kodeKab);
            response.data = kabupaten;
            response.isSuccess = true;
            response.message = "Data Ditemukan";
        } catch (Exception ex) {
            response.message = ex.getMessage();
            response.isSuccess = false;
        }
        return response;
    }

    @RequestMapping(value = "/kabupaten_marker", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject kabupatenMarker(
            HttpServletRequest request,
            @RequestParam(value = "kode_prov", required = false) String kodeProv, Principal principal) {
        //System.out.println("masuk kabupaten marker");
        ResponseObject response = new ResponseObject();
        try {
            UserApp user = userService.getUserByUsername(principal.getName());
            List<RelPpkPaket> rpp = rppd.findByIdUser(user);
            ArrayList<Paket> paket = new ArrayList<>();
            if (!rpp.isEmpty()) {
                for (Integer i = 0; i <= rpp.size(); i++) {
                    paket.add(rpp.get(i).getIdPaket());
                    if (i == rpp.size() - 1) {
                        break;
                    }
                }
            }

            Prov p = pd.findByKode(kodeProv);
            HashMap data = new HashMap();
            data.put("id", p.getId());
            data.put("kode", kodeProv);
            data.put("nama", p.getNama());
            data.put("type", "propinsi");
            data.put("x_coord", p.getXCoord());
            data.put("y_coord", p.getYCoord());

            List<LokasiSurvey> lokasi;
            if (!paket.isEmpty()) {
                lokasi = ls.findByIdPaketAndKodeProv(paket, kodeProv);
            } else {
                lokasi = ls.findByKodeProv(kodeProv);
            }

            HashMap dataLokasi = new HashMap();

            if (!lokasi.isEmpty()) {
                for (Integer i = 0; i <= lokasi.size(); i++) {
                    HashMap lks = new HashMap();
                    lks.put("id", lokasi.get(i).getId());
                    lks.put("kode", "");
                    lks.put("nama", lokasi.get(i).getAlamat());
                    lks.put("type", "lokasiSurvey");
                    lks.put("x_coord", lokasi.get(i).getLattitude());
                    lks.put("y_coord", lokasi.get(i).getLongitude());
                    dataLokasi.put(i, lks);

                    if (i == lokasi.size() - 1) {
                        dataLokasi.put(i + 1, data);
                        break;
                    }
                }
            } else {
                dataLokasi.put(0, data);
            }

            response.data = dataLokasi;
            response.isSuccess = true;
            response.message = "Data Ditemukan";
        } catch (Exception ex) {
            response.message = ex.getMessage();
            response.isSuccess = false;
        }
        return response;
    }

    @RequestMapping(value = "/kecamatan_marker", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject kecamatanMarker(
            HttpServletRequest request,
            @RequestParam(value = "kode_kab", required = false) String kodeKab, Principal principal) {
//        System.out.println("masuk Kecamatan Marker");
//        System.out.println(kodeKab);
        ResponseObject response = new ResponseObject();
        try {
            UserApp user = userService.getUserByUsername(principal.getName());
            List<RelPpkPaket> rpp = rppd.findByIdUser(user);
            ArrayList<Paket> paket = new ArrayList<>();
            if (!rpp.isEmpty()) {
                for (Integer i = 0; i <= rpp.size(); i++) {
                    paket.add(rpp.get(i).getIdPaket());
                    if (i == rpp.size() - 1) {
                        break;
                    }
                };
            }

            List<LokasiSurvey> lokasi = new ArrayList<>();

            Kab k = kd.findByKode(kodeKab);

            HashMap data = new HashMap();

            data.put("id", k.getId());
            data.put("kode", kodeKab);
            data.put("nama", k.getNamaKab());
            data.put("type", "kabupaten");
            data.put("x_coord", k.getxCoordinate());
            data.put("y_coord", k.getyCoordinate());

            if (!paket.isEmpty()) {
                lokasi = ls.findByIdPaketAndKodeKab(paket, kodeKab);
            } else {
                lokasi = ls.findByKodeKab(kodeKab);
            }
            
            HashMap dataLokasi = new HashMap();
            if (!lokasi.isEmpty()) {
                for (Integer i = 0; i <= lokasi.size(); i++) {
                    HashMap lks = new HashMap();
                    lks.put("id", lokasi.get(i).getId());
                    lks.put("kode", "");
                    lks.put("nama", lokasi.get(i).getAlamat());
                    lks.put("type", "lokasiSurvey");
                    lks.put("x_coord", lokasi.get(i).getLattitude());
                    lks.put("y_coord", lokasi.get(i).getLongitude());
                    dataLokasi.put(i, lks);

                    if (i == lokasi.size() - 1) {
                        dataLokasi.put(i + 1, data);

                        break;
                    }
                }
            } else {
                dataLokasi.put(0, data);
            }
            response.data = dataLokasi;
            response.isSuccess = true;
            response.message = "Data Ditemukan";
        } catch (Exception ex) {
            response.message = ex.getMessage();
            response.isSuccess = false;
        }
        return response;
    }

    @RequestMapping(value = "/get_count_data", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject getCountData(HttpServletRequest request,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "id", required = true) String id) {
//        System.out.println("masuk get count data");
//        System.out.println(type);
//        System.out.println(id);
        ResponseObject response = new ResponseObject();
        try {
            if (type.equals("lokasiSurvey")) {
                String foto_kondisi_lahan = "<table><tr>";
                String foto_kondisi_jalan = "<table><tr>";
                String foto_kondisi_air = "<table><tr>";
                String baseUrl = request.getContextPath().toString();
                List<LokasiImages> lokasiImages = gls.findByIdLokasi(id);
                
                if (lokasiImages.size() != 0) {
                    for (Integer i = 0; i <= lokasiImages.size(); i++) {
                        if (lokasiImages.get(i).getType() != null) {
                            if (lokasiImages.get(i).getType() == 1) {
                                foto_kondisi_lahan += "<td><a href=\"" + baseUrl + lokasiImages.get(i).getFile() + "\" data-lightbox=\"example-set\" data-title=\"" + lokasiImages.get(i).getKeterangan() + "\"><img src=\"" + baseUrl + lokasiImages.get(i).getFile() + "\" style=\"width:125px; padding:5px;\"/></a></td>";
                            } else if (lokasiImages.get(i).getType() == 2) {
                                foto_kondisi_jalan += "<td><a href=\"" + baseUrl + lokasiImages.get(i).getFile() + "\" data-lightbox=\"example-set\" data-title=\"" + lokasiImages.get(i).getKeterangan() + "\"><img src=\"" + baseUrl + lokasiImages.get(i).getFile() + "\" style=\"width:125px; padding:5px;\"/></a></td>";
                            } else if (lokasiImages.get(i).getType() == 3) {
                                foto_kondisi_air += "<td><a href=\"" + baseUrl + lokasiImages.get(i).getFile() + "\" data-lightbox=\"example-set\" data-title=\"" + lokasiImages.get(i).getKeterangan() + "\"><img src=\"" + baseUrl + lokasiImages.get(i).getFile() + "\" style=\"width:125px; padding:5px;\"/></a></td>";
                            }
                        } else {
                            foto_kondisi_lahan += "<td><a href=\"" + baseUrl + lokasiImages.get(i).getFile() + "\" data-lightbox=\"example-set\" data-title=\"" + lokasiImages.get(i).getKeterangan() + "\"><img src=\"" + baseUrl + lokasiImages.get(i).getFile() + "\" style=\"width:125px; padding:5px;\"/></a></td>";
                        }
                        if (i == lokasiImages.size() - 1) {
                            break;
                        }
                    }
                }
                foto_kondisi_lahan += "</tr></table>";
                foto_kondisi_jalan += "</tr></table>";
                foto_kondisi_air += "</tr></table>";

                List<LaporanMingguan> lm = lmd.findByIdLokasiLeftJoin(id);
                
                String konsultanMk = "";
                String kontraktorPelaksana = "";
                String rencana = "";
                String realisasi = "";
                String deviasi = "";
                String pekerjaanMingguIni = "";
                String permasalahanHambatan = "";
                String idLaporan = "";
                
                if (!lm.isEmpty()) {
                    for (Integer i = 0; i <= lm.size(); i++) {
                        konsultanMk = lm.get(0).getIdLokasi().getNamaKonsultanMk();
                        kontraktorPelaksana = lm.get(0).getIdLokasi().getKontraktorPelaksana();
                        rencana = lm.get(0).getRencanaProgressMingguIni();
                        realisasi = lm.get(0).getProgressMingguIni();
                        deviasi = lm.get(0).getDeviasi();
                        pekerjaanMingguIni = lm.get(0).getAktifitasPokokMingguIni();
                        permasalahanHambatan = lm.get(0).getPermasalahanHambatan();
                        idLaporan = lm.get(0).getId();
                    }
                }
                
                String foto_progress = "<table><tr>";

                if (idLaporan != null) {
                    List<LaporanMingguanImages> lmi = lmid.findByIdLaporan(idLaporan);
                    if (!lmi.isEmpty()) {
                        for (Integer i = 0; i <= lmi.size(); i++) {
                            foto_progress += "<td><a href=\"" + baseUrl + lmi.get(i).getFile() + "\" data-lightbox=\"example-set\" data-title=\"" + lmi.get(i).getKeterangan() + "\"><img src=\"" + baseUrl + lmi.get(i).getFile() + "\" style=\"width:125px; padding:5px;\"></a></td>";
                            if (i == lmi.size() - 1) {
                                break;
                            }
                        }
                    }
                }
                foto_progress += "</tr></table>";

                LokasiSurvey lokasi = ls.findById(id);
                HashMap data = new HashMap();
                data.put("id_lokasi", lokasi.getId());
                data.put("nama_surveyor", lokasi.getNamaSurveyor());
                data.put("nama_konsultan_mk", lokasi.getNamaKonsultanMk());
                data.put("nama_lokasi", lokasi.getNamaLokasi());
                data.put("tanggal_survey", lokasi.getTanggalSurvey());
                data.put("nama_lembaga_pengusul", lokasi.getNamaLembagaPengusul());
                data.put("kontak_person", lokasi.getKontakPerson());
                data.put("target_grup", lokasi.getTargetGrup());
                data.put("jml_tb", lokasi.getJmlTb());
                data.put("lebar_jalan_kondisi_akses", lokasi.getLebarJalanKondisiAkses());
                data.put("sumber_listrik_jarak_lokasi", lokasi.getSumberListrikJarakLokasi());
                data.put("sumber_air_jarak_lokasi", lokasi.getSumberAirJarakLokasi());
                data.put("jarak_dengan_pusat_kegiatan", lokasi.getJarakDenganPusatKegiatan());
                data.put("luas_lahan_usulan", lokasi.getLuasLahanUsulan());
                data.put("status_tanah", lokasi.getStatusTanah());
                data.put("jenis_tanah", lokasi.getJenisTanah());
                data.put("kondisi_fisik", lokasi.getKondisiFisik());
                data.put("keterangan", lokasi.getKeterangan());
                data.put("rekomendasi", lokasi.getRekomendasi());
                data.put("alamat", lokasi.getAlamat());
                data.put("foto", foto_kondisi_lahan);
                data.put("foto2", foto_kondisi_jalan);
                data.put("foto3", foto_kondisi_air);
                data.put("foto_progress", foto_progress);
                data.put("konsultan_mk_wilayah", konsultanMk);
                data.put("kontraktor_pelaksana", kontraktorPelaksana);
                data.put("rencana", rencana);
                data.put("realisasi", realisasi);
                data.put("deviasi", deviasi);
                data.put("pekerjaan_minggu_ini", pekerjaanMingguIni);
                data.put("permasalahan_hambatan", permasalahanHambatan);
                data.put("link_laporan", "<a href=\"" + baseUrl + "/admin/master/master_laporan_mingguan/laporan/" + idLaporan + "\" target=_blank>klik untuk lihat laporan</a>");
                response.data = data;
                response.isSuccess = true;
            } else {
                response.isSuccess = false;
                response.message = "data bukan tipe lokasiSurvey";
            }
        } catch (Exception ex) {
            response.message = ex.getMessage();
            response.isSuccess = false;
        }
        return response;
    }
}
