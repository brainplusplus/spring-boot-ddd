package com.app.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.master.KabupatenService;
import com.app.service.master.KecamatanService;
import com.app.service.master.MasterSatkerService;
import com.app.service.master.PageService;
import com.app.service.master.PaketService;
import com.app.service.master.ProvinsiService;
import com.app.service.master.RoleService;
import com.app.service.master.UserService;
import com.app.service.master.WilayahService;

@RestController
@RequestMapping("/rest/combobox")
public class ComboboxRestController {

	@Autowired
	private ProvinsiService pss;
	@Autowired
	private KabupatenService kabss;
	@Autowired
	private KecamatanService kecss;
	@Autowired
	private RoleService rs;
	@Autowired
	private PageService ps;
        @Autowired
	private PaketService pkts;
        @Autowired
        private MasterSatkerService mss;
        @Autowired
        private UserService us;
        @Autowired
        private WilayahService ws;
	

	@RequestMapping(value = "/cmb_prov", method = RequestMethod.GET)
	public Object cmb_prov() {
		return pss.findAll();
	}

	@RequestMapping(value = "/cmb_kab/{kodeProv}", method = RequestMethod.GET)
	public Object cmb_kab(@PathVariable("kodeProv") String kodeProv) {
		return kabss.searchListByIdProv(kodeProv);
	}

	@RequestMapping(value = "/cmb_kec/{kodeKab}", method = RequestMethod.GET)
	public Object cmb_kec(@PathVariable("kodeKab") String kodeKab) {
		return kecss.searchListByIdKab(kodeKab);
	}
	
	@RequestMapping(value = "/cmb_role", method = RequestMethod.GET)
	public Object cmb_role() {
		return rs.findAll();
	}
	
	@RequestMapping(value = "/cmb_page", method = RequestMethod.GET)
	public Object cmb_page() {
		return ps.findAll();
	}
        
        @RequestMapping(value = "/cmb_paket", method = RequestMethod.GET)
	public Object cmb_paket() {
		return pkts.findAll();
	}
        
        @RequestMapping(value = "/cmb_satker", method = RequestMethod.GET)
	public Object cmb_satker() {
		return mss.findAll();
	}
        
        @RequestMapping(value = "/cmb_user", method = RequestMethod.GET)
	public Object cmb_user() {
		return us.findAll();
	}
        
        @RequestMapping(value = "/cmb_wilayah", method = RequestMethod.GET)
	public Object cmb_wilayah() {
		return ws.findAll();
	}
        
        @RequestMapping(value = "/cmb_pengawas", method = RequestMethod.GET)
	public Object cmb_pengawas() {
		return us.getUserByRolePengawas();
	}
        
        @RequestMapping(value = "/cmb_rekanan", method = RequestMethod.GET)
	public Object cmb_rekanan() {
		return us.getUserByRoleRekanan();
	}

}
