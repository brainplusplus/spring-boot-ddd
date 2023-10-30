package com.app.controller.view.master;

import com.app.entity.PageApp;
import com.app.entity.Role;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.service.master.PageService;
import com.app.service.master.RolePageService;
import com.app.service.master.RoleService;

/**
 * Created by driyanh on 06/11/2015.
 */
@Controller
@RequestMapping("/admin/master/master_role_page")
public class MasterRolePageViewController {
	
	@Autowired
	private RoleService rps;
	
	@Autowired
	private PageService ps;

	@RequestMapping("/index/{role_id}")
	public String index(@PathVariable("role_id") String roleId, Model model) {
		model.addAttribute("id", roleId);
		Role role = rps.findOne(roleId);
		model.addAttribute("nama", role.getNama());
		return "view/master/master_role_page/index";
	}

	@RequestMapping("/form/{role_id}")
	public String form(@PathVariable("role_id") String roleId, Model model) {
		model.addAttribute("id", roleId);
		Role role = rps.findOne(roleId);
		model.addAttribute("nama", role.getNama());
		Iterable<PageApp> pages = ps.findAll();
		model.addAttribute("pages", pages);
		return "partial/master/master_role_page/form";
	}
}
