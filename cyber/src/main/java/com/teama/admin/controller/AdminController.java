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
	
	@GetMapping("newBook.do")
	public ModelAndView manageNewBook() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}

	@GetMapping("storageBook.do")
	public ModelAndView manageStorageBook() {
		ModelAndView mv = new ModelAndView("admin/storageBook");
		
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
	public ModelAndView registBestBook() {
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
		
		Map<String, Object> bookInfo = adminService.searchBook(commandMap.getMap());
		
		if (bookInfo != null) {
			List<Map<String, Object>> docs = (List<Map<String, Object>>)bookInfo.get("docs");
			Map<String, Object> docMap = docs.get(0);
			
			commandMap.put("author", (String)docMap.get("AUTHOR"));
		}
		
		mv.addObject("infoMap", commandMap.getMap());
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("registBook.do")
	public ModelAndView registBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/admin");

		Map<String, Object> bookInfo = adminService.searchBook(commandMap.getMap());
		
		if (bookInfo != null) {
			List<Map<String, Object>> docs = (List<Map<String, Object>>)bookInfo.get("docs");
			Map<String, Object> docMap = docs.get(0);

			adminService.registBook(docMap);
		}

		mv.addObject("infoMap", commandMap.getMap());
		
		List<Map<String, Object>> list = adminService.bookList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@PostMapping("searchStoredBook.do")
	public ModelAndView searchStoredBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/storageBook");
		
		List<Map<String, Object>> list = adminService.storedBookList(commandMap.getMap());
		mv.addObject("list", list);
		
		return mv;
	}
	
	@PostMapping("notStoredBooks.do")
	public ModelAndView notStoredBooks() {
		ModelAndView mv = new ModelAndView("admin/storageBook");
		
		List<Map<String, Object>> list = adminService.notStoredBookList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@GetMapping("updateStoredBook.do")
	public ModelAndView updateStoredBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/storageBook");
		
		adminService.updateStoredBook(commandMap.getMap());
		
		return mv;
	}
	
	@GetMapping("loanPopup.do")
	public ModelAndView loanPopup(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/loanPopup");
		
		List<Map<String, Object>> list = adminService.bookLoanListByMemberNo(commandMap.getMap());
		mv.addObject("loanList", list);
		
		return mv;
	}
	

	@PostMapping("searchMember.do")
	public ModelAndView searchMember(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/member");
		
		List<Map<String, Object>> list = adminService.searchMember(commandMap.getMap());
		mv.addObject("list", list);
		
		return mv;
	}
}
