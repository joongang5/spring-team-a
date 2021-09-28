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
import com.teama.admin.service.SearchType;
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
	public ModelAndView registRecommendBook() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("searchBook.do")
	public ModelAndView searchBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		String searchType = (String)commandMap.get("searchType");
		String searchValue = (String)commandMap.get("searchValue");
		
		Map<String, Object> bookInfo = adminService.searchBook(SearchType.valueOf(searchType), searchValue);
		
		if (bookInfo != null) {
			List<Map<String, Object>> docs = (List<Map<String, Object>>)bookInfo.get("docs");
			Map<String, Object> docMap = docs.get(0);
			
			commandMap.put("author", (String)docMap.get("AUTHOR"));
			mv.addObject("infoMap", commandMap.getMap());
		}
		
		mv.addObject("searchType", searchType);
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("registBook.do")
	public ModelAndView registBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/admin");

		String searchType = (String)commandMap.get("searchType");
		String searchValue = (String)commandMap.get("searchValue");
		
		Map<String, Object> bookInfo = adminService.searchBook(SearchType.valueOf(searchType), searchValue);
		
		if (bookInfo != null) {
			List<Map<String, Object>> docs = (List<Map<String, Object>>)bookInfo.get("docs");
			Map<String, Object> docMap = docs.get(0);

			adminService.registBook(docMap);
		}

		mv.addObject("searchType", searchType);
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}
}
