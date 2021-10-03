package com.teama.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.admin.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource(name="adminService")
	private AdminService adminService;

	@GetMapping("home.do")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("admin/admin");
		return mv;
	}
}
