/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller.view.master;

import com.app.entity.LokasiImages;
import com.app.entity.LokasiSurvey;
import com.app.service.master.GambarLokasiService;
import com.app.service.master.LokasiService;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dimasrh <ryan.hawari at gmail.com>
 */
@Controller
@RequestMapping("/admin/master/master_laporan_data_umum")
public class MasterLaporanDataUmumViewController {

    @Autowired
    private LokasiService ls;
    @Autowired
    private GambarLokasiService gls;

    @RequestMapping("/index/{lokasi_id}")
    public String index(@PathVariable("lokasi_id") String lokasiId, Model model) throws IllegalAccessException, InvocationTargetException {
        LokasiSurvey lokasi = ls.findById(lokasiId);
        List<LokasiImages> gambar = gls.findByIdLokasi(lokasiId);
        model.addAttribute("lokasi", lokasi);
        model.addAttribute("gambar", gambar);
        model.addAttribute("jmlgambar", gambar.size());
        model.addAttribute("trBuka", "<tr>");
        model.addAttribute("trTutup", "</tr>");
        return "view/master/master_laporan_data_umum/index";
    }

}
