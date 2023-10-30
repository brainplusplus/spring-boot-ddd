package com.app.controller.view.dashboard;

import com.app.dao.DashboarSatkerDao;
import com.app.entity.DashboardLokasiEntity;
import com.app.entity.DashboardPaketEntity;
import com.app.entity.DashboardPpkEntity;
import com.app.entity.DashboardSatkerEntity;
import com.app.object.dashboard.DashboardSatkerObject;
import com.app.service.dashboard.DashboardSatkerViewService;
import com.app.service.master.DashboardLokasiService;
import com.app.service.master.DashboardPaketService;
import com.app.service.master.DashboardSatkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
 * =====================================================================================================================
 * Please DO NOT use TAB, use space instead!
 * =====================================================================================================================
 */

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardSatkerViewController {

    @Autowired
    DashboardSatkerViewService dashboardSatkerService;

    @Autowired
    DashboardSatkerService dashboardPpkService;

    @Autowired
    DashboardPaketService dashboardPaketService;

    @Autowired
    DashboardLokasiService dashboardLokasiService;

    @RequestMapping("/index")
    public String index(Model model) {
		System.out.println("MASUK A");
        DashboardSatkerObject data = dashboardSatkerService.getData();
        System.out.println("MASUK B"+data);
        model.addAttribute("dataDashboardSatker", data.listDashboardSatker);
		System.out.println("MASUK C");
        model.addAttribute("jumlahSatuan", data.jumlahSatuan);
        model.addAttribute("jumlahLokasi", data.jumlahLokasi);
        model.addAttribute("avgRencana", data.avgRencana);
        model.addAttribute("avgRealisasi", data.avgRealisasi);
        model.addAttribute("avgDeviasi", data.avgDeviasi);
        model.addAttribute("jumlahCepat", data.jumlahCepat + " lokasi");
        model.addAttribute("jumlahLambat", data.jumlahLambat + " lokasi");

        model.addAttribute("avgDeviasiBackColor", data.avgDeviasiBackColor);

        return "/view/dashboard/dashboard_satker/index";
    }

    @RequestMapping("/ppk/{id}")
    public String ppk(@PathVariable("id") String id, Model model) {

        List<DashboardPpkEntity> dashboardPpkEntityList = dashboardPpkService.getListByIdSatker(id);
        model.addAttribute("dataDashboardPpk", dashboardPpkEntityList);

        /*
         * Ugly af
         */
        String namaSatuanJajaran = "";
        for (DashboardPpkEntity dataDashboard : dashboardPpkEntityList) {
            namaSatuanJajaran = dataDashboard.getNamaSatker();
            break;
        }
        model.addAttribute("namaSatuanJajaran", "SATUAN JAJARAN " + namaSatuanJajaran);

        return "/view/dashboard/dashboard_ppk/index";
    }

    @RequestMapping("/paket/{id}")
    public String paket(@PathVariable("id") String id, Model model) {

        List<DashboardPaketEntity> dashboardPaketEntityList = dashboardPaketService.getListByIdUserPaket(id);
        model.addAttribute("dataDashboardPaket", dashboardPaketEntityList);

        /*
         * Ugly af
         */
        String namaJajaran = "";
        for (DashboardPaketEntity dashboardPaketEntity : dashboardPaketEntityList) {
            namaJajaran = dashboardPaketEntity.getNamaPpk();

            System.out.println("Nama PPK: " + dashboardPaketEntity.getNamaPpk());
        }
        model.addAttribute("namaJajaran", "JAJARAN " + namaJajaran);

        return "/view/dashboard/dashboard_paket/index";
    }

    @RequestMapping("/lokasi/{id}")
    public String lokasi(@PathVariable("id") String id, Model model) {

        List<DashboardLokasiEntity> dashboardLokasiEntitites = dashboardLokasiService.getListByIdPaket(id);
        model.addAttribute("dataDashboardLokasi", dashboardLokasiEntitites);

        /*
         * Ugly af
         */
        String namaPaket = "";
        for (DashboardLokasiEntity dashboardLokasiEntity : dashboardLokasiEntitites) {
            namaPaket = dashboardLokasiEntity.getNamaPaket();

            System.out.println("Nama Paket: " + dashboardLokasiEntity.getNamaPaket());
        }
        model.addAttribute("namaPaket", namaPaket);

        return "/view/dashboard/dashboard_lokasi/index";
    }
}
