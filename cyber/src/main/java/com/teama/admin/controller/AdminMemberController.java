package com.teama.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.loan.dto.LoanViewDTO;
import com.teama.loan.service.LoanService;
import com.teama.member.dto.MemberDTO;
import com.teama.member.service.MemberService;
import com.teama.util.Util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

	@Resource(name="memberService")
	private MemberService memberService;
	@Resource(name="loanService")
	private LoanService loanService;
	
	@GetMapping("home.do")
	public ModelAndView home(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/member");

		int currentPageNo = commandMap.getIntValue("pageNo", 1);
		int totalRecordCount = memberService.getTotalCount();
		
		PaginationInfo paginationInfo = Util.newPaginationInfo(currentPageNo, totalRecordCount);

		int firstRecordIndex = paginationInfo.getFirstRecordIndex();
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();
		
		List<MemberDTO> memberDTOList = memberService.getMemberList(firstRecordIndex, recordCountPerPage);
		mv.addObject("memberDTOList", memberDTOList);
		mv.addObject("paginationInfo", paginationInfo);
		
		return mv;
	}
	
	@PostMapping("searchMember.do")
	public ModelAndView searchMember(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/member");
		
		String searchType = commandMap.getStrValue("searchType");
		mv.addObject("searchType", searchType);
		String searchValue = commandMap.getStrValue("keyword");
		mv.addObject("keyword", searchValue);
		
		List<MemberDTO> memberDTOList = memberService.getMemberList(searchType, searchValue);
		mv.addObject("memberDTOList", memberDTOList);
		
		return mv;
	}

	@GetMapping("showPopup.do")
	public ModelAndView showPopup(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/loanPopup");

		int memberNo = commandMap.getIntValue("memberNo");
		mv.addObject("memberNo", memberNo);

		List<LoanViewDTO> loanViewDTOList = loanService.getViewListByMemberNo(memberNo);
		mv.addObject("loanViewDTOList", loanViewDTOList);
		
		return mv;
	}
}
