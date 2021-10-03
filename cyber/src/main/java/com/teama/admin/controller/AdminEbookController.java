package com.teama.admin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.ebook.dto.EbookDTO;
import com.teama.ebook.service.EbookAPIServiceImpl;
import com.teama.ebook.service.EbookService;

@Controller
@RequestMapping("/admin/ebook")
public class AdminEbookController {

	@Resource(name="ebookService")
	private EbookService ebookService;
	@Resource(name="ebookAPIService")
	private EbookAPIServiceImpl ebookAPIService;
	
	@GetMapping("home.do")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("admin/ebook");

		List<EbookDTO> ebookDTOList = ebookService.getEbookList();
		mv.addObject("ebookDTOList", ebookDTOList);
		
		return mv;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("searchBook.do")
	public ModelAndView searchBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/ebook");
		
		String searchType = commandMap.getStrValue("searchType");
		String searchValue = commandMap.getStrValue("searchValue");

		Map<String, Object> bookInfo = ebookAPIService.searchEbook(searchType, searchValue, 1);
		if (bookInfo != null) {
			List<Map<String, Object>> docs = (List<Map<String, Object>>)bookInfo.get("docs");
			Map<String, Object> docMap = docs.get(0);
			
			commandMap.put("author", (String)docMap.get("AUTHOR"));
		}
		
		mv.addObject("commandMap", commandMap.getMap());
		
		List<EbookDTO> ebookDTOList = ebookService.getEbookList();
		mv.addObject("ebookDTOList", ebookDTOList);
		
		return mv;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("registBook.do")
	public ModelAndView registBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/ebook");
		
		String searchType = commandMap.getStrValue("searchType");
		String searchValue = commandMap.getStrValue("searchValue");
		mv.addObject("commandMap", commandMap.getMap());
		
		Map<String, Object> bookInfo = ebookAPIService.searchEbook(searchType, searchValue, 1);
		
		if (bookInfo != null) {
			List<Map<String, Object>> docs = (List<Map<String, Object>>)bookInfo.get("docs");
			Map<String, Object> docMap = docs.get(0);

			ebookService.insertBook(docMap);
		}

		List<EbookDTO> ebookDTOList = ebookService.getEbookList();
		mv.addObject("ebookDTOList", ebookDTOList);
		
		return mv;
	}
	
	@PostMapping("registBestBook.do")
	public ModelAndView registBestBook() {
		ModelAndView mv = new ModelAndView("admin/ebook");

		List<EbookDTO> ebookDTOList = ebookService.getEbookList();
		mv.addObject("ebookDTOList", ebookDTOList);
		
		return mv;
	}

	@PostMapping("registRecommendBook.do")
	public ModelAndView registRecommendBook() {
		ModelAndView mv = new ModelAndView("admin/ebook");
		
		List<EbookDTO> ebookDTOList = ebookService.getEbookList();
		mv.addObject("ebookDTOList", ebookDTOList);
		
		return mv;
	}
}
