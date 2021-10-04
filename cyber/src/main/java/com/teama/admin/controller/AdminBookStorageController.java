package com.teama.admin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.storage.dto.BookStorageDTO;
import com.teama.storage.dto.BookStorageViewDTO;
import com.teama.storage.service.BookStorageService;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/admin/storage")
public class AdminBookStorageController {

	@Resource(name="bookStorageService")
	private BookStorageService bookStorageService;
	
	@RequestMapping("home.do")
	public ModelAndView home(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/bookStorage");

		int currentPageNo = commandMap.getIntValue("pageNo", 1);
		int totalRecordCount = bookStorageService.getTotalCount();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(currentPageNo);
		paginationInfo.setRecordCountPerPage(3);
		paginationInfo.setPageSize(8);
		paginationInfo.setTotalRecordCount(totalRecordCount);
		
		int firstRecordIndex = paginationInfo.getFirstRecordIndex();
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();
		
		List<Map<String, Object>> bookStorageViewDTOList = bookStorageService.getPagingBookMapList(firstRecordIndex, recordCountPerPage);
		mv.addObject("bookStorageViewDTOList", bookStorageViewDTOList);
		mv.addObject("paginationInfo", paginationInfo);
		
		return mv;
	}

	@PostMapping("getBook.do")
	public ModelAndView getBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/bookStorage");

		int bookNo = commandMap.getIntValue("bookNo");
		
		BookStorageViewDTO bookStorageViewDTO = bookStorageService.getBook(bookNo);
		List<BookStorageViewDTO> bookStorageViewDTOList = List.of(bookStorageViewDTO);
		
		mv.addObject("bookStorageViewDTOList", bookStorageViewDTOList);
		
		return mv;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value="getBookAJAX.do", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getBookAJAX(CommandMap commandMap) {
		int bookNo = commandMap.getIntValue("bookNo");
		
		Map<String, Object> bookStorageViewDTO = bookStorageService.getBookMap(bookNo);
		List<Map<String, Object>> bookStorageViewDTOList = List.of(bookStorageViewDTO);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("bookStorageViewDTOList", bookStorageViewDTOList);
		
		return jsonObj.toString();
	}
	
	@PostMapping("unregisteredBook.do")
	public ModelAndView unregisteredBook() {
		ModelAndView mv = new ModelAndView("admin/bookStorage");
		
		List<BookStorageViewDTO> bookStorageViewDTOList = bookStorageService.getUnregisteredBookList();
		mv.addObject("bookStorageViewDTOList", bookStorageViewDTOList);
		
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value="unregisteredBookAJAX.do", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String unregisteredBookAJAX() {
		
		List<Map<String, Object>> bookStorageViewDTOList = bookStorageService.getUnregisteredBookMapList();

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("bookStorageViewDTOList", bookStorageViewDTOList);
		
		return jsonObj.toString();
	}
	
	@GetMapping("updateBook.do")
	public ModelAndView updateBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/bookStorage");
		
		int bookNo = commandMap.getIntValue("bookNo");
		int maxCount = commandMap.getIntValue("maxCount");
		
		BookStorageDTO dto = new BookStorageDTO();
		dto.setBook_no(bookNo);
		dto.setMax_count(maxCount);
		
		bookStorageService.updateMaxCount(dto);
		
		return mv;
	}
}
