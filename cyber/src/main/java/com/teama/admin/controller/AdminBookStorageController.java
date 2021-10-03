package com.teama.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.storage.dto.BookStorageDTO;
import com.teama.storage.dto.BookStorageViewDTO;
import com.teama.storage.service.BookStorageService;

@Controller
@RequestMapping("/admin/storage")
public class AdminBookStorageController {

	@Resource(name="bookStorageService")
	private BookStorageService bookStorageService;
	
	@GetMapping("home.do")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("admin/storageBook");
		
		List<BookStorageViewDTO> bookStorageViewDTOList = bookStorageService.getBookList();
		mv.addObject("bookStorageViewDTOList", bookStorageViewDTOList);
		
		return mv;
	}

	@PostMapping("getBook.do")
	public ModelAndView getBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/storageBook");

		int bookNo = commandMap.getIntValue("bookNo");
		
		BookStorageViewDTO bookStorageViewDTO = bookStorageService.getBook(bookNo);
		List<BookStorageViewDTO> bookStorageViewDTOList = List.of(bookStorageViewDTO);
		
		mv.addObject("bookStorageViewDTOList", bookStorageViewDTOList);
		
		return mv;
	}
	
	@PostMapping("unregisteredBook.do")
	public ModelAndView unregisteredBook() {
		ModelAndView mv = new ModelAndView("admin/storageBook");
		
		List<BookStorageViewDTO> list = bookStorageService.getUnregisteredBookList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@GetMapping("updateBook.do")
	public ModelAndView updateBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/storageBook");
		
		int bookNo = commandMap.getIntValue("bookNo");
		int maxCount = commandMap.getIntValue("maxCount");
		
		BookStorageDTO dto = new BookStorageDTO();
		dto.setBook_no(bookNo);
		dto.setMax_count(maxCount);
		
		bookStorageService.updateMaxCount(dto);
		
		return mv;
	}
}
