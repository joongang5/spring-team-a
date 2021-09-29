package com.teama.ebook.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ebookMain.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String ebookSearch(CommandMap map, HttpServletRequest request) throws Exception {
		if(!map.containsKey("pageNo")) {
			map.put("pageNo", 1);
		}
		JSONObject EbookList = listEbookService.ebookSearch(map.getMap());
		//System.out.println(map.getMap().get("searchTarget"));
		//System.out.println(map.getMap().get("searchValue"));
		if(map.getMap().get("searchValue")!=null) {
			EbookList.put("searchTarget", map.getMap().get("searchTarget"));
			EbookList.put("searchValue", map.getMap().get("searchValue"));
		}
		
		return EbookList.toJSONString();
	}
	
	@GetMapping("/ebookDetail")
	public ModelAndView ebookDetail(CommandMap map) {
		ModelAndView mv = new ModelAndView();
		
		
		
		return mv;		
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
