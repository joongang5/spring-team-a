package com.teama.member.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.ebook.service.EbookService;
import com.teama.loan.dto.LoanViewDTO;
import com.teama.loan.service.LoanService;
import com.teama.member.service.MyPageService;
import com.teama.util.Util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
	@Resource(name="myPageService")
	private MyPageService myPageService;
	@Resource(name="loanService")
	private LoanService loanService;
	@Resource(name="ebookService")
	private EbookService ebookService;
	
	@GetMapping("myPage.do")
	private ModelAndView myPage(HttpServletRequest request, CommandMap map) {
		ModelAndView mv = new ModelAndView("member/myPage");
		
		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = Util.parseInt(memberNoObj);
			map.put("memberNo", memberNo);
			int ltotalCount = loanService.ltotalCount(map.getMap());
			int reserveCount = loanService.reserveCount(map.getMap());
			
			List<LoanViewDTO> loanViewList = loanService.getViewListByMemberNo(map.getMap());
			
			mv.addObject("loanViewList", loanViewList);
			mv.addObject("ltotalCount", ltotalCount);
			mv.addObject("reserveCount", reserveCount);
		}
		return mv;
	}
	
	@GetMapping("ebookLoanList.do")
	private ModelAndView ebookLoanList(HttpServletRequest request, CommandMap map) {
		ModelAndView mv = new ModelAndView("ebook/ebookLoanList");
		
		int pageNo = 1;
		
		if (map.containsKey("pageNo")) {
			pageNo= Integer.parseInt(String.valueOf(map.get("pageNo")));
		}
		int listScale = 4;
		int pageScale = 10;

		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = Util.parseInt(memberNoObj);
			map.put("memberNo", memberNo);
			int totalCount = loanService.totalCount(map.getMap());
			//System.out.println(totalCount + "개가 있습니다.");
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(pageNo);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			paginationInfo.setTotalRecordCount(totalCount);//
			
			int startPage = paginationInfo.getFirstRecordIndex();
			int lastPage = paginationInfo.getRecordCountPerPage();
			
			//System.out.println(startPage);
			//System.out.println(lastPage);
			
			map.put("startPage", startPage);
			map.put("lastPage", lastPage);
			
			List<LoanViewDTO> loanViewDTOList = loanService.getViewPagingListByMemberNo(map.getMap());
			
			mv.addObject("loanViewDTOList", loanViewDTOList);
			mv.addObject("paginationInfo", paginationInfo);
			mv.addObject("pageNo", pageNo);
			mv.addObject("totalCount", totalCount);
			
		}
		//System.out.println("last Map : "+map.getMap());
		return mv;
	}
	
	@GetMapping("doReturn.do")
	private ModelAndView doReturn(HttpServletRequest request, CommandMap map) {
		ModelAndView mv = new ModelAndView("ebook/ebookLoanList");
		
		int bookNo = map.getIntValue("bookNo");
		
		int pageNo = 1;
		if (map.containsKey("pageNo")) {
			pageNo= Integer.parseInt(String.valueOf(map.get("pageNo")));
		}
		int listScale = 4;
		int pageScale = 10;

		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = Util.parseInt(memberNoObj);
			map.put("memberNo", memberNo);
			int totalCount = loanService.totalCount(map.getMap());
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(pageNo);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			paginationInfo.setTotalRecordCount(totalCount);//
			
			int startPage = paginationInfo.getFirstRecordIndex();
			int lastPage = paginationInfo.getRecordCountPerPage();
			
			map.put("startPage", startPage);
			map.put("lastPage", lastPage);
			
			List<LoanViewDTO> loanViewDTOList = loanService.getViewPagingListByMemberNo(map.getMap());
			
			mv.addObject("loanViewDTOList", loanViewDTOList);
			mv.addObject("paginationInfo", paginationInfo);
			mv.addObject("pageNo", pageNo);
			mv.addObject("totalCount", totalCount);
			
			loanService.doReturn(bookNo, memberNo);
		}
		return mv;
	}
	
	@GetMapping("doExtend.do")
	private ModelAndView doExtend(HttpServletRequest request, CommandMap map) {
		ModelAndView mv = new ModelAndView("ebook/ebookLoanList");
		
		int bookNo = map.getIntValue("bookNo");
		
		int pageNo = 1;
		if (map.containsKey("pageNo")) {
			pageNo= Integer.parseInt(String.valueOf(map.get("pageNo")));
		}
		int listScale = 4;
		int pageScale = 10;

		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = Util.parseInt(memberNoObj);
			map.put("memberNo", memberNo);
			int totalCount = loanService.totalCount(map.getMap());
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(pageNo);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			paginationInfo.setTotalRecordCount(totalCount);//
			
			int startPage = paginationInfo.getFirstRecordIndex();
			int lastPage = paginationInfo.getRecordCountPerPage();
			
			map.put("startPage", startPage);
			map.put("lastPage", lastPage);
			
			List<LoanViewDTO> loanViewDTOList = loanService.getViewPagingListByMemberNo(map.getMap());
			
			mv.addObject("loanViewDTOList", loanViewDTOList);
			mv.addObject("paginationInfo", paginationInfo);
			mv.addObject("pageNo", pageNo);
			mv.addObject("totalCount", totalCount);
			
			loanService.doExtend(bookNo, memberNo);
		}
		return mv;
	}
}
