package com.teama.ebook.controller;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ebookMain.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String ebookSearch(CommandMap map) throws Exception {
		System.out.println("POST");
		if(!map.containsKey("pageNo")) {
			map.put("pageNo", 1);
		}
		JSONObject searchList = listEbookService.ebookSearch(map.getMap());
		searchList.put("search", map.getMap().get("title"));
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
