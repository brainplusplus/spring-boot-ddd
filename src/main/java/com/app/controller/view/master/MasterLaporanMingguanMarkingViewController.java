package com.app.controller.view.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.LaporanMingguan;
import com.app.service.master.LaporanMingguanService;
import com.app.service.master.LokasiService;
import com.app.service.master.PaketService;


@Controller
@RequestMapping("/admin/master/master_laporan_mingguan_marking")
public class MasterLaporanMingguanMarkingViewController {
	
	@Autowired
	private LokasiService ls;
	
//	@Autowired
	private PaketService ps;
	
	@Autowired
	private LaporanMingguanService lms; 
	

	@RequestMapping("/index/{lm_id}")
	public String index(@PathVariable("lm_id") String lmId,Model model) {
		model.addAttribute("id", lmId);
		LaporanMingguan lm = lms.findOne(lmId);
//		System.out.println("======================= print ===============");
//		System.out.println(lmId);
//		System.out.println("======================= print ===============");
//		System.out.println(lm);
//		System.out.println(lm.getIdLokasi());
//		System.out.println("======================= print ===============");
		model.addAttribute("mingguke",lm.getLaporanPeriodeMingguKe());
		model.addAttribute("lokasi",lm.getIdLokasi().getAlamat());
		model.addAttribute("paket",lm.getIdLokasi().getIdPaket().getNamaPaket());
		return "view/master/master_laporan_mingguan_marking/index";
	}

	@RequestMapping("/form/{lm_id}")
	public String form(@PathVariable("lm_id") String lmId,Model model) {
		model.addAttribute("id", lmId);
		LaporanMingguan lm = lms.findOne(lmId);
		model.addAttribute("mingguke",lm.getLaporanPeriodeMingguKe());
		model.addAttribute("lokasi",lm.getIdLokasi().getAlamat());
		model.addAttribute("paket",lm.getIdLokasi().getIdPaket().getNamaPaket());
		return "partial/master/master_laporan_mingguan_marking/form";
	}
}
