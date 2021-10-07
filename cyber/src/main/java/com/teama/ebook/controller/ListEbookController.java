package com.teama.ebook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParser;
import com.teama.common.CommandMap;
import com.teama.ebook.dto.EbookDTO;
import com.teama.ebook.service.EbookAPIService;
import com.teama.ebook.service.EbookAPIServiceImpl;
import com.teama.ebook.service.EbookServiceImpl;

@Controller
@RequestMapping("/ebook")
public class ListEbookController {

	@Resource(name = "ebookService")
	private EbookServiceImpl ebookService;
	@Resource(name = "ebookAPIService")
	private EbookAPIServiceImpl ebookAPIService;

	@GetMapping("ebookMain.do")
	public ModelAndView ebookMain(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("ebook/ebookMain");
		if (!map.containsKey("pageNo")) {
			map.put("pageNo", 1);
		}
		// map.put("searchTarget", "isbn");
		// map.put("searchValue", "9791163032816");

		// System.out.println(map.get("pageNo"));
		// System.out.println(map.getMap().get("searchTarget"));
		// System.out.println(map.getMap().get("searchValue"));
		List<EbookDTO> EbookList = ebookService.ebookSearch(map.getMap());
		for (EbookDTO list : EbookList) {
			if (list.getTitle_url().equals("")) {
				List<Map<String, Object>> kakao = ebookAPIService.ebookSearchKakao(list.getIsbn());
				// 도서 썸네일 없을시 카카오에서 가져오기
				if (kakao != null) {
					list.setTitle_url((String) kakao.get(0).get("thumbnail"));
				} else {
					list.setTitle_url(null);
				}
			}
		}
//		if(map.getMap().get("searchValue")!=null) {
//			EbookList.put("searchTarget", map.getMap().get("searchTarget"));
//			EbookList.put("searchValue", map.getMap().get("searchValue"));
//		}
		mv.addObject("EbookList", EbookList);
		return mv;
	}

	@RequestMapping(value = "/ebookMain.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView ebookSearch(CommandMap map, HttpServletRequest request) throws Exception {

		return null;
	}

	@RequestMapping(value = "/ebooklist.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public List<EbookDTO> ebookList(CommandMap map) throws Exception {
		System.out.println(map.getMap().get("pageNo"));
		if (!map.containsKey("pageNo")) {
			map.put("pageNo", 1);
		}
		// map.put("searchTarget", "isbn");
		// map.put("searchValue", "9791163032816");

		System.out.println(map.get("pageNo"));
		// System.out.println(map.getMap().get("searchTarget"));
		// System.out.println(map.getMap().get("searchValue"));
		List<EbookDTO> EbookList = ebookService.ebookSearch(map.getMap());
//		if(map.getMap().get("searchValue")!=null) {
//			EbookList.put("searchTarget", map.getMap().get("searchTarget"));
//			EbookList.put("searchValue", map.getMap().get("searchValue"));
//		}
		System.out.println(EbookList);
		return EbookList;
	}

	@GetMapping("/ebookDetail.do")
	public ModelAndView ebookDetail(EbookDTO bookInfo) throws Exception {
		ModelAndView mv = new ModelAndView("ebook/ebookDetail");
		String isbn = bookInfo.getIsbn();
		EbookDTO EbookDetail = ebookService.ebookDetail(isbn);
		List<Map<String, Object>> kakao = ebookAPIService.ebookSearchKakao(isbn);
		if (kakao != null) {
			if (EbookDetail.getTitle_url().equals("")) {
				EbookDetail.setTitle_url((String) kakao.get(0).get("thumbnail"));
			} else {
				EbookDetail.setTitle_url(null);
			}
			String URL = String.valueOf(kakao.get(0).get("url"));
			System.out.println(URL);
			Document doc = Jsoup.connect(URL).get();
			
			Elements elem = doc.select("div.info_section");
			Elements content = elem.select("p.desc");
			System.out.println("content -----------" + content);
//		System.out.println(elem.size());
			Map<String, Object> detail = new HashMap<String, Object>();
			for (int i = 0; i < content.size(); i++) {
				detail.put("detail" + i, content.get(i).text());
			}
			System.out.println("detail ===== " +detail.get("detail1"));
			mv.addObject("detail", detail);
		}
		mv.addObject("ebookDetail", EbookDetail);
		return mv;
	}

}
