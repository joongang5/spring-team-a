package com.teama.notice.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.notice.service.NoticeServiceImpl;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping("/bbs")
public class NoticeController {

	@Resource(name="noticeService")
	private NoticeServiceImpl noticeService;
	
	//게시글 불러오기
	@RequestMapping("/listNotice.do")
	public ModelAndView noticeList(CommandMap map) {
		ModelAndView mv = new ModelAndView("notice/listNotice");
		
		//페이지 번호가 오는지 확인하기
		int pageNo = 1; //첫 페이지 보여주세요.
		if(map.containsKey("pageNo")) {
			pageNo = Integer.parseInt( String.valueOf (map.get("pageNo")) );
		}
		int listScale = 10;
		int pageScale = 10;
		
		//totalCount
		
		int totalCount = noticeService.totalCount(map.getMap());
		System.out.println(totalCount + "개가 있습니다.");
		
		//전자정부 페이징 불러오기
		PaginationInfo paginationInfo = new PaginationInfo(); //이 객체에 값을 넣어주어야 한다.
		paginationInfo.setCurrentPageNo(pageNo);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		paginationInfo.setTotalRecordCount(totalCount);
		
		//계산하기
		int startPage = paginationInfo.getFirstRecordIndex(); //시작페이지
		int lastPage = paginationInfo.getRecordCountPerPage(); //마지막페이지
		
		//DB로 보내기 위해서 map에 담아주세요.
		map.put("startPage", startPage);
		map.put("lastPage", lastPage);
		
		//질의
		List<Map<String, Object>> list = noticeService.noticeList(map.getMap());
		
		//담기
		mv.addObject("list", list); //앞엔 호출이름, 뒤는 값
		mv.addObject("paginationInfo", paginationInfo);
		mv.addObject("pageNo", pageNo);
		mv.addObject("totalCount", totalCount);
		
		return mv;
	}
	
	//게시글 상세보기
	@RequestMapping("/noticeDetail.do")
	public ModelAndView detail(CommandMap map) {
		ModelAndView mv = new ModelAndView("notice/noticeDetail");
		Map<String, Object> detail = noticeService.detail(map.getMap());
		mv.addObject("detail", detail);
		return mv;
	}
	
	//게시글 글쓰기
	
	//게시글 삭제
	@RequestMapping("/delete.do")
	public String delete(CommandMap map) {
		int result = noticeService.delete(map.getMap());
		System.out.println("delete = " + result);

		return "redirect:listNotice.do";
	}
	
	//게시글 수정
	
}
