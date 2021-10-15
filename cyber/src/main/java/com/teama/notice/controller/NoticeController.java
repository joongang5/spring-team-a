package com.teama.notice.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		//검색값을 jsp로 넘기기
		if(map.containsKey("searchKeyword")) {
			mv.addObject("searchKeyword", map.get("searchKeyword"));
			mv.addObject("searchCondition", map.get("searchCondition"));
		}
		
		setPagination(map, mv);
		
		return mv;
	}
	
	//게시글 상세보기
	@RequestMapping("/noticeDetail.do")
	public ModelAndView detail(CommandMap map) {
		ModelAndView mv = new ModelAndView("notice/noticeDetail");
		
		//조회수
		noticeService.count(map.getMap());
		
		Map<String, Object> detail = noticeService.detail(map.getMap());
		Map<String, Object> preNextPage = noticeService.preNextPage(map.getMap());
		mv.addObject("detail", detail);
		mv.addObject("preNextPage", preNextPage);
		return mv;
	}
	
	//게시글 상세보기 -> 글쓰기 페이지로 넘기기
	@GetMapping("/noticeWrite.do")
	public String write() {
		return "notice/noticeWrite";
	}
	
	//값 받아서 글쓰기
	@PostMapping("/noticeWrite.do")
	public ModelAndView postWrite(CommandMap map, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("notice/listNotice");
		
		HttpSession session = request.getSession();
		map.put("id", session.getAttribute("id"));
		
		int result = noticeService.write(map.getMap());
		System.out.println("noticeWrite = " + result);
		
		setPagination(map, mv);
		
		return mv;
	}
	
	//게시글 삭제
	@RequestMapping("/noticeDelete.do")
	public String delete(CommandMap map, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		map.put("id", session.getAttribute("id"));
		
		int result = noticeService.delete(map.getMap());
		System.out.println("noticeDelete = " + result);

		return "redirect:listNotice.do";
	}
	
	//게시글 상세보기 -> 수정하기 페이지로 값 가지고 넘기기
	@GetMapping("/noticeUpdate.do")
	public ModelAndView update(CommandMap map) {
		ModelAndView mv = new ModelAndView("notice/noticeUpdate");
		Map<String, Object> detail = noticeService.detail(map.getMap());
		mv.addObject("detail", detail);
		return mv;
	}
	
	//값 받아서 수정하기
	@PostMapping("/noticeUpdate.do")
	public String postUpdate(CommandMap map, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		map.put("id", session.getAttribute("id"));
		
		int result = noticeService.update(map.getMap());
		System.out.println("noticeUpdate = " + result);
		return "redirect:noticeDetail.do?no="+map.get("no");
	}
	
	//페이징
	private void setPagination(CommandMap map, ModelAndView mv) {
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

		mv.addObject("list", list); //앞엔 호출이름, 뒤는 값
		mv.addObject("paginationInfo", paginationInfo);
		mv.addObject("pageNo", pageNo);
		mv.addObject("totalCount", totalCount);
	}
}
