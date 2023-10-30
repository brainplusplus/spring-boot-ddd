/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.controller.view.master;

import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author dimasrh <ryan.hawari at gmail.com>
 */

@Controller
@RequestMapping("/admin/master/master_lokasi_survey")
public class MasterLokasiViewController {
    
        @RequestMapping("/index")
	public String index(Model model) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                model.addAttribute("role", auth.getAuthorities().toString());
		return "view/master/master_lokasi_survey/index";
	}

	@RequestMapping("/form")
	public String form(Model model) {

		return "partial/master/master_lokasi/form";
	}

}
