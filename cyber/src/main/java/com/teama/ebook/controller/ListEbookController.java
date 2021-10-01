package com.teama.ebook.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.ebook.service.EbookAPIServiceImpl;
import com.teama.ebook.service.ListEbookServiceImpl;

@Controller
public class ListEbookController {

	@Resource(name="listEbookService")
	private ListEbookServiceImpl listEbookService;
	@Resource(name="ebookAPIService")
	private EbookAPIServiceImpl ebookAPIService;
	
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
		//map.put("searchTarget", "isbn");
		//map.put("searchValue", "9791163032816");
		
		System.out.println(map.get("pageNo"));
		JSONObject EbookList = ebookAPIService.ebookSearch(map.getMap());
		//System.out.println(map.getMap().get("searchTarget"));
		//System.out.println(map.getMap().get("searchValue"));
		if(map.getMap().get("searchValue")!=null) {
			EbookList.put("searchTarget", map.getMap().get("searchTarget"));
			EbookList.put("searchValue", map.getMap().get("searchValue"));
		}
		
		return EbookList.toJSONString();
	}
	
	@GetMapping("/ebookDetail")
	public ModelAndView ebookDetail(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("ebook/ebookDetail");
		String URL = "https://search.daum.net/search?w=bookpage&bookId=3761145&q=%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD%EC%97%90+%EC%9D%B4%EB%9F%B0+%ED%95%99%EA%B5%90%EA%B0%80+%EC%9E%88%EC%97%88%EC%96%B4%3F";
		Document doc = Jsoup.connect(URL).get();

		Elements elem = doc.select("div.info_section");
		Elements content = elem.select("p.desc");
		//System.out.println(content);
//		System.out.println(elem.size());
		Map<String, Object> detail = new HashMap<String, Object>();
		for(int i = 0; i < content.size(); i++) {
			detail.put("detail"+i, content.get(i).text());			
		}
		System.out.println(detail.get("detail1"));
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
	@GetMapping("/ebookAdd")
	public String ebookAdd(CommandMap map, HttpServletRequest request) throws Exception {
			
		map.put("pageNo", 1);
		
		int EbookList = listEbookService.ebookAdd(map.getMap());
		
		for (int i = 2; i < 10; i++) {
			map.put("pageNo", i);
			listEbookService.ebookAdd(map.getMap());
		}
		return null;
	}
}
