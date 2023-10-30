package com.app.controller.view.master;

import com.app.entity.LokasiSurvey;
import com.app.service.master.LokasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/master/realisasi")
public class MasterRealisasiController {

    @Autowired
    private LokasiService lokasiService;

    @RequestMapping("/index/{lokasi_id}")
    public String index(
            @PathVariable("lokasi_id") String lokasiId,
            Model model
    ) {
        /*
         * Cari lokasi_survey sesuai `lokasi_id`, set ke view (nanti)
         */
        model.addAttribute("id", lokasiId);
        LokasiSurvey lokasi = lokasiService.findOne(lokasiId);
        model.addAttribute("provinsi",lokasi.getKodeProv().getNama())
                .addAttribute("idLokasi",lokasi.getId())
                .addAttribute("kabupaten",lokasi.getKodeKab().getNamaKab())
                .addAttribute("lokasi",lokasi.getNamaLokasi())
                .addAttribute("alamat",lokasi.getAlamat())
                .addAttribute("no_kontrak",lokasi.getNoKontrak());

        return "view/master/master_realisasi/index";
    }

    @RequestMapping("/form/{lokasi_id}")
    public String form(
            @PathVariable("lokasi_id") String lokasiId,
            Model model
    ) {

        /*
         * Set static dropdown value using HashMap
         */
        Map< String, String > jenisRealisasiData = new HashMap<String, String>();
        jenisRealisasiData.put("UANG_MUKA", "Uang Muka");
        jenisRealisasiData.put("TERMIN_1", "Termin 1");
        jenisRealisasiData.put("TERMIN_2", "Termin 2");
        jenisRealisasiData.put("TERMIN_3", "Termin 3");
        jenisRealisasiData.put("TERMIN_4", "Termin 4");

        /*
         * Cari lokasi_survey sesuai `lokasi_id`, set ke view (nanti)
         */
        model.addAttribute("id", lokasiId);
        LokasiSurvey lokasi = lokasiService.findOne(lokasiId);
        model.addAttribute("provinsi",lokasi.getKodeProv().getNama())
                .addAttribute("idLokasi",lokasi.getId())
                .addAttribute("kabupaten",lokasi.getKodeKab().getNamaKab())
                .addAttribute("lokasi",lokasi.getNamaLokasi())
                .addAttribute("alamat",lokasi.getAlamat())
                .addAttribute("no_kontrak",lokasi.getNoKontrak())
                .addAttribute("jenisRealisasiList", jenisRealisasiData);



        return "partial/master/master_realisasi/form";
    }
}
