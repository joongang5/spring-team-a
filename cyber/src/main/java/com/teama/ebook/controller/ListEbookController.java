package com.teama.ebook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.ebook.dto.EbookDTO;
import com.teama.ebook.dto.EbookReviewDTO;
import com.teama.ebook.service.EbookAPIService;
import com.teama.ebook.service.EbookAPIServiceImpl;
import com.teama.ebook.service.EbookServiceImpl;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

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
		
		PaginationInfo paginationInfo = new PaginationInfo();
		int pageNo = 1; //현재 페이지 번호
		int listScale = 10; //한 페이지에 나올 글 수
		int pageScale = 10; // 페이지 개수
		// map.put("searchTarget", "isbn");
		// map.put("searchValue", "9791163032816");

		// System.out.println(map.get("pageNo"));
		//System.out.println(map.get("searchTarget"));
		//System.out.println(map.get("searchValue"));
		if(map.get("searchValue") != null) {
			System.out.println("검색어 없음");
			mv.addObject("searchTarget", map.get("searchTarget"));
			mv.addObject("searchValue", map.get("searchValue"));
		}
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
		System.out.println(startPage);
		System.out.println(lastPage);
		map.put("startPage", startPage);
		map.put("lastPage", lastPage);
		List<EbookDTO> EbookList = ebookService.getEbookList(map.getMap());
		
		
		if(!EbookList.isEmpty()) {
			paginationInfo.setTotalRecordCount(EbookList.get(0).getTotalCount());
			
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
			
		}
		mv.addObject("pageNo", pageNo);
		mv.addObject("paginationInfo", paginationInfo);
		return mv;
	}

	@RequestMapping(value = "/ebookMain.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView ebookSearch(CommandMap map, HttpServletRequest request) throws Exception {

		return null;
	}

	@RequestMapping(value = "/ebookSearch.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public List<EbookDTO> ebookSearch(CommandMap map) throws Exception {
		System.out.println(map.getMap().get("pageNo"));
		if (!map.containsKey("pageNo")) {
			map.put("pageNo", 1);
		}
		// map.put("searchTarget", "isbn");
		// map.put("searchValue", "9791163032816");

		System.out.println(map.get("pageNo"));
		// System.out.println(map.getMap().get("searchTarget"));
		// System.out.println(map.getMap().get("searchValue"));
		List<EbookDTO> EbookList = ((EbookAPIService) ebookService).ebookSearch(map.getMap());
//		if(map.getMap().get("searchValue")!=null) {
//			EbookList.put("searchTarget", map.getMap().get("searchTarget"));
//			EbookList.put("searchValue", map.getMap().get("searchValue"));
//		}
		System.out.println("POST");
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
			}
			String URL = String.valueOf(kakao.get(0).get("url"));
			System.out.println(URL);
			Document doc = Jsoup.connect(URL).get();
			
			doc.outputSettings().prettyPrint(false);
			Elements content = doc.select("div.info_section").select("p.desc");
			System.out.println("content=" +  content);
			//Elements content = elem.select("p.desc");
			//크롤링시 줄바꿈 문제 해결
			//System.out.println("content -----------" + content);
//		System.out.println(elem.size());
			Map<String, Object> detail = new HashMap<String, Object>();
			for (int i = 0; i < content.size(); i++) {
				detail.put("detail" + i, content.get(i).html());
			}
			System.out.println("detail ===== " +detail.get("detail1"));
			mv.addObject("detail", detail);
		}
		//System.out.println(EbookDetail.getNo());
		List<EbookReviewDTO> review = ebookService.ebookReviewList(EbookDetail.getNo());
		if(!review.isEmpty()) {
			mv.addObject("ebookReview", review);
		}
		mv.addObject("ebookDetail", EbookDetail);
		return mv;
	}
	@PostMapping("/ebookReview")
	public String ebookReview(EbookReviewDTO dto, HttpServletRequest request) {
		dto.setId((String) request.getSession().getAttribute("id"));
		int result = ebookService.ebookInsertReview(dto);
		System.out.println(result);
		System.out.println(request.getParameter("isbn"));
		System.out.println(request.getSession().getAttribute("id"));
		return "redirect:ebookDetail.do?isbn="+request.getParameter("isbn");
	}

}
