package com.teama.ebook.controller;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.ebook.service.ListEbookServiceImpl;

@Controller
public class ListEbookController {

	@Resource(name="listEbookService")
	private ListEbookServiceImpl listEbookService;
	
	@GetMapping("ebookMain.do")
	public String ebookMain(CommandMap map) throws Exception {
		return "ebook/ebookMain";
	}
	@RequestMapping(value = "/ebookMain.do", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String ebookMain2(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("ebook/ebookMain");
		System.out.println("GET");
		System.out.println(map.getMap().get("title"));
		JSONObject searchList = listEbookService.ebookSearch(map.getMap());
		mv.addObject("totalCount", searchList.get("TOTAL_COUNT"));
		mv.addObject("pageNo", searchList.get("PAGE_NO"));
		mv.addObject("searchList", searchList.get("docs"));
		
		return searchList.toJSONString();
	}
	@RequestMapping(value = "/ebookMain.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String ebookMain3(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("ebook/ebookMain");
		JSONObject searchList = listEbookService.ebookSearch(map.getMap());
		System.out.println(map.getMap().get("title"));
		mv.addObject("totalCount", searchList.get("TOTAL_COUNT"));
		mv.addObject("pageNo", searchList.get("PAGE_NO"));
		mv.addObject("searchList", searchList.get("docs"));
		System.out.println("POST");
		return searchList.toJSONString();
	}
//	@PostMapping("ebookMain.do")
//	public ModelAndView ebookSearch(CommandMap map) throws Exception {
//		ModelAndView mv = new ModelAndView("ebook/ebookMain");
//		JSONObject searchList = listEbookService.ebookSearch(map.getMap());
//		mv.addObject("totalCount", searchList.get("TOTAL_COUNT"));
//		mv.addObject("pageNo", searchList.get("PAGE_NO"));
//		mv.addObject("searchList", searchList.get("docs"));
//		return mv;
//	}
}
