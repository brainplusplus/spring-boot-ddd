package com.app.controller.view.master;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by driyanh on 06/11/2015.
 */
@Controller
@RequestMapping("/admin/master/master_role")
public class MasterRoleViewController {

	@RequestMapping("/index")
	public String index(Model model) {
		return "view/master/master_role/index";
	}

	@RequestMapping("/form")
	public String form(Model model) {
		return "partial/master/master_role/form";
	}
}
