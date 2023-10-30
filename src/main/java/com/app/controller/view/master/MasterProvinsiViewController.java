package com.app.controller.view.master;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hendra @09/11/2015.
 */
@Controller
@RequestMapping("/admin/master/master_provinsi")
public class MasterProvinsiViewController {

	@RequestMapping("/index")
	public String index(Model model) {
		return "view/master/master_provinsi/index";
	}

	@RequestMapping("/form")
	public String form(Model model) {
		return "partial/master/master_provinsi/form";
	}
}
