package com.teama.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.admin.service.AdminService;
import com.teama.loan.dto.LoanViewDTO;
import com.teama.loan.service.LoanService;
import com.teama.member.dto.MemberDTO;
import com.teama.member.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource(name="adminService")
	private AdminService adminService;
	@Resource(name="memberService")
	private MemberService memberService;
	@Resource(name="loanService")
	private LoanService loanService;

	@GetMapping("home.do")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("admin/admin");
		
		int todayLoanCount = loanService.todayLoanCount(0);
		mv.addObject("todayLoanCount", todayLoanCount);
		int todayReserveCount = loanService.todayLoanCount(1);
		mv.addObject("todayReserveCount", todayReserveCount);
		int todayReturnCount = loanService.todayLoanCount(2);
		mv.addObject("todayReturnCount", todayReturnCount);
		int todayJoinCount = memberService.todayJoinCount();
		mv.addObject("todayJoinCount", todayJoinCount);
		
		List<MemberDTO> memberDTOList = memberService.getRecentlyMemberList(5);
		mv.addObject("memberDTOList", memberDTOList);
		
		List<LoanViewDTO> loanViewDTOList = loanService.getRecentlyViewList(5);
		mv.addObject("loanViewDTOList", loanViewDTOList);
		
		return mv;
	}
}
