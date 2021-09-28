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
import com.teama.common.CommandMap;

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

	@PostMapping("registBestBook.do")
	public ModelAndView bookBest() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}

	@PostMapping("registRecommendBook.do")
	public ModelAndView bookRecommend() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@PostMapping("searchBook.do")
	public ModelAndView bookSearch(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		long isbn = 0;
		String strIsbn = (String)commandMap.get("isbn");
		if (strIsbn.isEmpty() == false)
			isbn = Long.parseLong(strIsbn);
		
		Map<String, Object> bookInfo = adminService.searchBook(isbn);
		
		commandMap.put("author", (String)bookInfo.get("auther"));
		mv.addObject("infoMap", commandMap.getMap());
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@PostMapping("registBook.do")
	public ModelAndView bookRegist() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}
}