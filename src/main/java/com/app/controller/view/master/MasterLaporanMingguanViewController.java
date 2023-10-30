package com.app.controller.view.master;

import com.app.entity.LaporanMingguan;
import com.app.entity.LaporanMingguanImages;
import com.app.entity.LaporanMingguanMarking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.LokasiSurvey;
import com.app.service.master.LaporanMingguanImagesService;
import com.app.service.master.LaporanMingguanMarkingService;
import com.app.service.master.LaporanMingguanService;
import com.app.service.master.LaporanMingguanTampakImagesService;
import com.app.service.master.LokasiService;
import com.app.service.master.PaketService;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
@RequestMapping("/admin/master/master_laporan_mingguan")
public class MasterLaporanMingguanViewController {
	
	@Autowired
	private LokasiService ls;
	
	private PaketService ps;
        
        @Autowired
        private LaporanMingguanService lms;
        
        @Autowired
        private LaporanMingguanImagesService lmis;
        
        @Autowired
        private LaporanMingguanMarkingService lmms;
        
        
	@RequestMapping("/index/{lokasi_id}")
	public String index(@PathVariable("lokasi_id") String lokasiId,Model model) {
		model.addAttribute("id", lokasiId);
		LokasiSurvey lokasi = ls.findOne(lokasiId);
		model.addAttribute("lokasi",lokasi.getAlamat());
		model.addAttribute("paket",lokasi.getIdPaket().getNamaPaket());
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                model.addAttribute("role", auth.getAuthorities().toString());
		return "view/master/master_laporan_mingguan/index";
	}

	@RequestMapping("/form/{lokasi_id}")
	public String form(@PathVariable("lokasi_id") String lokasiId,Model model) {
		model.addAttribute("id", lokasiId);
		LokasiSurvey lokasi = ls.findOne(lokasiId);
		model.addAttribute("lokasi",lokasi.getAlamat());
		model.addAttribute("paket",lokasi.getIdPaket().getNamaPaket());
		return "partial/master/master_laporan_mingguan/form";
	}
        
        @RequestMapping("/laporan/{laporanId}")
	public String laporan(@PathVariable("laporanId") String laporanId,Model model) {
		model.addAttribute("id", laporanId);
		LaporanMingguan laporan = lms.findOne(laporanId);
                List<LaporanMingguanImages> gambarMingguan = lmis.findByIdLaporan(laporanId);
                List<LaporanMingguanMarking> gambarMarking = lmms.findByIdLaporan(laporanId);
                model.addAttribute("laporan", laporan);
                model.addAttribute("lokasi", laporan.getIdLokasi());
                model.addAttribute("paket", laporan.getIdLokasi().getIdPaket());
                model.addAttribute("mingguanGambar", gambarMingguan);
                model.addAttribute("mingguanGambarCount", gambarMingguan.size());
                model.addAttribute("buktiGambar", gambarMarking);
                model.addAttribute("buktiGambarCount", gambarMarking.size());
                model.addAttribute("trBuka", "<tr>");
                model.addAttribute("trTutup", "</tr>");
		return "partial/laporan_mingguan";
//                return "view/master/master_laporan_mingguan/laporan_mingguan";
	}
        
}
