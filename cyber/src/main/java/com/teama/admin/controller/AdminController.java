package com.teama.admin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.admin.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource(name="adminService")
	private AdminService adminService;
	
	@GetMapping("book.do")
	public ModelAndView book() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}

	@GetMapping("member.do")
	public ModelAndView member() {
		ModelAndView mv = new ModelAndView("admin/member");
		
		List<Map<String, Object>> list = adminService.memberList();
		mv.addObject("list", list);
		
		return mv;
	}

	@PostMapping("bookBest.do")
	public ModelAndView bookBest() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}

	@PostMapping("bookRecommend.do")
	public ModelAndView bookRecommend() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@PostMapping("bookSearch.do")
	public ModelAndView bookSearch() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		System.out.println("bookSearch");
		
		return mv;
	}
	
	@PostMapping("bookRegist.do")
	public ModelAndView bookRegist() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}
}
