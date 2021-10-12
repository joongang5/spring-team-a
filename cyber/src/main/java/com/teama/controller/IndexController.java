package com.teama.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.service.IndexService;
import com.teama.storage.dto.BookStorageViewDTO;
import com.teama.storage.service.BookStorageService;

@Controller
public class IndexController {

	@Resource(name = "indexService")
	private IndexService indexService;

	@Resource(name = "bookStorageService")
	private BookStorageService bookStorageService;

	@GetMapping("index.do")
	public ModelAndView main() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<BookStorageViewDTO> bookStorageViewDTOList = bookStorageService.getPopularViewList();
		mv.addObject("bookStorageViewDTOList", bookStorageViewDTOList);
		return mv;
	}
}