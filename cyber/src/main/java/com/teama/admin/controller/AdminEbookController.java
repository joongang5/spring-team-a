package com.teama.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.ebook.dto.EbookDTO;
import com.teama.ebook.service.EbookAPIServiceImpl;
import com.teama.ebook.service.EbookService;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/admin/ebook")
public class AdminEbookController {

	@Resource(name="ebookService")
	private EbookService ebookService;
	@Resource(name="ebookAPIService")
	private EbookAPIServiceImpl ebookAPIService;
	
	@GetMapping("home.do")
	public ModelAndView home(CommandMap map) {
		ModelAndView mv = new ModelAndView("admin/ebook");
		PaginationInfo paginationInfo = new PaginationInfo();
		int pageNo = 1; //현재 페이지 번호
		int listScale = 10; //한 페이지에 나올 글 수
		int pageScale = 10;
		if (!map.containsKey("pageNo")) {
			pageNo = 1;
		}else {
			pageNo= Integer.parseInt((String) map.get("pageNo"));
		}
		paginationInfo.setCurrentPageNo(pageNo);	//현재 페이지 번호
		paginationInfo.setRecordCountPerPage(listScale);	//한 페이지에 나올 글 수
		paginationInfo.setPageSize(pageScale);	//페이지 개수
		int startPage = paginationInfo.getFirstRecordIndex(); // 시작페이지
		int lastPage = paginationInfo.getRecordCountPerPage(); // 마지막페이지
		map.put("startPage", startPage);
		map.put("lastPage", lastPage);

		List<EbookDTO> ebookDTOList = ebookService.getEbookList(map.getMap());
		
		paginationInfo.setTotalRecordCount(ebookDTOList.get(0).getTotalCount());
		if(map.containsKey("searchValue")) {
			mv.addObject("commandMap", map.getMap());
		}
		mv.addObject("pageNo", pageNo);
		mv.addObject("paginationInfo", paginationInfo);
		mv.addObject("ebookDTOList", ebookDTOList);
		return mv;
	}

	@PostMapping("searchBook.do")
	public ModelAndView searchBook(CommandMap map) {
		ModelAndView mv = new ModelAndView("admin/ebook");
		
		String searchType = map.getStrValue("searchType");
		String searchValue = map.getStrValue("searchValue");
		List<EbookDTO> bookInfo = ebookAPIService.searchEbook(searchType, searchValue, 1);
		
		if (bookInfo.size() != 0) {
			//commandMap.put("author", bookInfo.get(0).getAuthors());
			mv.addObject("bookInfo", bookInfo.get(0));
		}
		PaginationInfo paginationInfo = new PaginationInfo();
		int pageNo = 1; //현재 페이지 번호
		int listScale = 10; //한 페이지에 나올 글 수
		int pageScale = 10;
		if (!map.containsKey("pageNo")) {
			pageNo = 1;
		}else {
			pageNo= Integer.parseInt((String) map.get("pageNo"));
		}
		paginationInfo.setCurrentPageNo(pageNo);	//현재 페이지 번호
		paginationInfo.setRecordCountPerPage(listScale);	//한 페이지에 나올 글 수
		paginationInfo.setPageSize(pageScale);	//페이지 개수
		int startPage = paginationInfo.getFirstRecordIndex(); // 시작페이지
		int lastPage = paginationInfo.getRecordCountPerPage(); // 마지막페이지
		map.put("startPage", startPage);
		map.put("lastPage", lastPage);
		
		mv.addObject("bookInfoList", bookInfo);
		mv.addObject("commandMap", map.getMap());
		List<EbookDTO> ebookDTOList = ebookService.getEbookList(map.getMap());
		
		paginationInfo.setTotalRecordCount(ebookDTOList.get(0).getTotalCount());
		mv.addObject("pageNo", pageNo);
		mv.addObject("paginationInfo", paginationInfo);
		mv.addObject("ebookDTOList", ebookDTOList);
		return mv;
	}

	@PostMapping("registBook.do")
	public String registBook(CommandMap commandMap, HttpServletRequest request) {
		
		String searchType = "isbn";
		String[] valueArr = request.getParameterValues("valueArr");
		for (String searchValue : valueArr) {
			List<EbookDTO> bookInfo = ebookAPIService.searchEbook(searchType, searchValue, 1);
			ebookService.insertBook(bookInfo.get(0));
		}
		
		return "redirect:/registBook.do";
	}
	
	@PostMapping("registBestBook.do")
	public ModelAndView registBestBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/ebook");

		List<EbookDTO> ebookDTOList = ebookService.getEbookList(commandMap.getMap());
		mv.addObject("ebookDTOList", ebookDTOList);
		
		return mv;
	}

	@PostMapping("registRecommendBook.do")
	public ModelAndView registRecommendBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/ebook");
		
		List<EbookDTO> ebookDTOList = ebookService.getEbookList(commandMap.getMap());
		mv.addObject("ebookDTOList", ebookDTOList);
		
		return mv;
	}
}
