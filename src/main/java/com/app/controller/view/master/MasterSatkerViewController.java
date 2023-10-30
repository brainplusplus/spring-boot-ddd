/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller.view.master;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dimasrh <ryan.hawari at gmail.com>
 */
@Controller
@RequestMapping("/admin/master/master_satker")
public class MasterSatkerViewController {

    @RequestMapping("/index")
    public String index(Model model) {
        return "view/master/master_satker/index";
    }

    @RequestMapping("/form")
    public String form(Model model) {
        return "partial/master/master_satker/form";
    }

}
