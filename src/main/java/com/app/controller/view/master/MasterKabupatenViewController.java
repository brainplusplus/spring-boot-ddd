package com.app.controller.view.master;

import com.app.entity.Kab;
import com.app.service.master.KabupatenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hendra @09/11/2015.
 */
@Controller
@RequestMapping("/admin/master/master_kabupaten")
public class MasterKabupatenViewController {

	@RequestMapping("/index")
	public String index(Model model) {

		return "view/master/master_kabupaten/index";
	}

	@RequestMapping("/form")
	public String form(Model model) {
		return "partial/master/master_kabupaten/form";
	}
}
