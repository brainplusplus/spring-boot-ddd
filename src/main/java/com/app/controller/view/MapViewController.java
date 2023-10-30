/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.dom.Element;

/**
 *
 * @author dimasrh <ryan.hawari at gmail.com>
 */
@Controller
@RequestMapping("/admin/map")
public class MapViewController {

    @RequestMapping("/index")
	public String index(Model model) {
		return "partial/map";
	}

}
