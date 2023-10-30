package com.app.controller.view;

import com.app.entity.UserApp;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AppContextService;
import com.app.service.master.UserService;

@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    @RequestMapping({"/", "/admin/welcome_page"})
    public String welcome(Model hasil, Principal principal) {
        hasil.addAttribute("principal", principal);
        if (principal != null) {
            UserApp user = userService.getUserByUsername(principal.getName());
            hasil.addAttribute("user", user);
            return "view/welcome_page";
        } else {
            return "redirect: login";
        }
    }

    @RequestMapping("/login")
    public String login(Model hasil) {
        return "view/login";
    }

//    @RequestMapping("/admin/dashboard/index")
//    public String dshboard(Model hasil) {
//        return "view/master/master_laporan_mingguan_tampak_images/index";
//    }

    @RequestMapping("/admin/generate/{pass}")
    @ResponseBody
    public String dshboard(@PathVariable("pass") String password, Model hasil) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

}
