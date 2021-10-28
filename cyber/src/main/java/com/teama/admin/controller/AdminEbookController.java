package com.teama.admin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ModelAndView home(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("admin/ebook");
		PaginationInfo paginationInfo = new PaginationInfo();
		int pageNo = 1; //현재 페이지 번호
		int listScale = 10; //한 페이지에 나올 글 수
		int pageScale = 10;
		if (!map.containsKey("pageNo")) {
			pageNo = 1;
			map.put("pageNo", 1);
		}else {
			pageNo= Integer.parseInt((String) map.get("pageNo"));
		}
		paginationInfo.setCurrentPageNo(pageNo);	//현재 페이지 번호
		paginationInfo.setRecordCountPerPage(listScale);	//한 페이지에 나올 글 수
		paginationInfo.setPageSize(pageScale);	//페이지 개수

		List<EbookDTO> ebookDTOList = ebookAPIService.ebookSearch(map.getMap());
		if(ebookDTOList.get(0).getTotalCount()!=0) {
			paginationInfo.setTotalRecordCount(ebookDTOList.get(0).getTotalCount());
		}
		if(map.containsKey("searchValue")) {
			mv.addObject("commandMap", map.getMap());
		}
		mv.addObject("pageNo", pageNo);
		mv.addObject("paginationInfo", paginationInfo);
		mv.addObject("ebookDTOList", ebookDTOList);
		return mv;
	}

	@GetMapping("searchBook.do")
	public ModelAndView searchBook(CommandMap map) throws Exception {
		ModelAndView mv = new ModelAndView("admin/ebook");
		
		String searchType = map.getStrValue("searchType");
		String searchValue = map.getStrValue("searchValue");
		PaginationInfo paginationInfo = new PaginationInfo();
		int pageNo = 1; //현재 페이지 번호
		int listScale = 10; //한 페이지에 나올 글 수
		int pageScale = 10;
		if (!map.containsKey("pageNo")) {
			pageNo = 1;
			map.put("pageNo", 1);
		}else {
			pageNo= Integer.parseInt((String) map.get("pageNo"));
		}
		List<EbookDTO> ebookDTOList = ebookAPIService.searchEbook(searchType, searchValue, pageNo);
		paginationInfo.setCurrentPageNo(pageNo);	//현재 페이지 번호
		paginationInfo.setRecordCountPerPage(listScale);	//한 페이지에 나올 글 수
		paginationInfo.setPageSize(pageScale);	//페이지 개수
		
		if(ebookDTOList!=null && ebookDTOList.size()!=0) {
			paginationInfo.setTotalRecordCount(ebookDTOList.get(0).getTotalCount());
			mv.addObject("ebookDTOList", ebookDTOList);
			mv.addObject("bookInfo", ebookDTOList.get(0));
		}
		if(map.containsKey("searchValue")) {
			mv.addObject("commandMap", map.getMap());
		}
		mv.addObject("pageNo", pageNo);
		mv.addObject("paginationInfo", paginationInfo);
		
		if(!map.containsKey("pageNo")) {
			map.put("pageNo", 1);
		}
		mv.addObject("commandMap", map.getMap());
		
		return mv;
	}

	@PostMapping("registBook.do")
	@ResponseBody
	public String registBook(CommandMap commandMap, HttpServletRequest request) throws Exception {
		int failedCount = 0;
		String searchType = "isbn";
		String[] valueArr = request.getParameterValues("valueArr");
		for (String searchValue : valueArr) {
			List<EbookDTO> bookInfo = ebookAPIService.searchEbook(searchType, searchValue, 1);

			for (EbookDTO info : bookInfo) {
				if (info.getTitle_url().equals("")) {
					List<Map<String, Object>> kakao = ebookAPIService.ebookSearchKakao(info.getIsbn());
					// 도서 썸네일 없을시 카카오에서 가져와서 저장
					if (kakao != null) {
						info.setTitle_url((String) kakao.get(0).get("thumbnail"));
						break;
					} else {
						info.setTitle_url(null);
					}
				}
			}
			
			int insertResult = ebookService.insertBook(bookInfo.get(0));
			if (insertResult == 0)
				failedCount++;
		}
		String result = String.valueOf(valueArr.length - failedCount);
		//System.out.println(result);
		return result;
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
