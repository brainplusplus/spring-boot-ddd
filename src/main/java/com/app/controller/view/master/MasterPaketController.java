package com.app.controller.view.master;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/master/master_paket")
public class MasterPaketController {

    @RequestMapping("/index")
    public String index(Model model) {

        return "view/master/master_paket/index";
    }

    @RequestMapping("/form")
    public String form(Model model) {
        return "partial/master/master_paket/form";
    }
}
