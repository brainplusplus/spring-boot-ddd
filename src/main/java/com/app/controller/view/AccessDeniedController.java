package com.app.controller.view;

import com.app.entity.UserApp;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class AccessDeniedController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/access_denied")
	public String error(HttpServletRequest request, HttpServletResponse response,Model model,Principal principal,@RequestParam(value = "msg", required = false) String msg) {
		model.addAttribute("msg",msg);
		model.addAttribute("principal",principal);
		UserApp user = null;
		if(principal!=null)
			userService.getUserByUsername(principal.getName());
		model.addAttribute("user",user);
		return "view/access_denied";
	}

}
