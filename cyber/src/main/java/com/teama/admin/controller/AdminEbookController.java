package com.teama.admin.controller;

import java.util.List;

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

	@PostMapping("searchBook.do")
	public ModelAndView searchBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/ebook");
		
		String searchType = commandMap.getStrValue("searchType");
		String searchValue = commandMap.getStrValue("searchValue");
		System.out.println(searchType);
		List<EbookDTO> bookInfo = ebookAPIService.searchEbook(searchType, searchValue, 1);

		if (bookInfo != null) {
			//commandMap.put("author", bookInfo.get(0).getAuthors());
			mv.addObject("bookInfo", bookInfo.get(0));
		}
		mv.addObject("bookInfoList", bookInfo);
		mv.addObject("commandMap", commandMap.getMap());
		
		List<EbookDTO> ebookDTOList = ebookService.getEbookList();
		mv.addObject("ebookDTOList", ebookDTOList);
		
		return mv;
	}

	@PostMapping("registBook.do")
	public ModelAndView registBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/ebook");
		
		String searchType = commandMap.getStrValue("searchType");
		String searchValue = commandMap.getStrValue("searchValue");
		mv.addObject("commandMap", commandMap.getMap());
		
		List<EbookDTO> bookInfo = ebookAPIService.searchEbook(searchType, searchValue, 1);
		
		ebookService.insertBook(bookInfo.get(0));

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
