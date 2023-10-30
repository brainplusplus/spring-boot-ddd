package com.app.controller.view;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssetsController {

	@RequestMapping(value = "/assets/loadJs", produces = "text/javascript; charset=utf-8")
	public String welcome(@RequestParam(value = "path", required = true) String path, Model hasil,
			HttpServletResponse response) {
		response.setContentType("application/javascript");
		return "js/" + path;
	}
}
