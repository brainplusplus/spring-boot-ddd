package com.app.controller.view.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.LokasiSurvey;
import com.app.service.master.LokasiService;


@Controller
@RequestMapping("/admin/master/master_gambar_lokasi")
public class MasterGambarLokasiViewController {
	
	@Autowired
	private LokasiService ls;
	
	@RequestMapping("/index/{lokasi_id}")
	public String index(@PathVariable("lokasi_id") String lokasiId,Model model) {
		model.addAttribute("id", lokasiId);
		LokasiSurvey lokasi = ls.findOne(lokasiId);
		model.addAttribute("lokasi",lokasi.getAlamat());
		return "view/master/master_gambar_lokasi/index";
	}

	@RequestMapping("/form/{lokasi_id}")
	public String form(@PathVariable("lokasi_id") String lokasiId,Model model) {
		model.addAttribute("id", lokasiId);
		LokasiSurvey lokasi = ls.findOne(lokasiId);
		model.addAttribute("lokasi",lokasi.getAlamat());
		return "partial/master/master_gambar_lokasi/form";
	}
}
