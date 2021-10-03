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
import com.teama.storage.dto.BookStorageDTO;
import com.teama.storage.service.BookStorageService;

@Controller
@RequestMapping("/admin/loan")
public class AdminLoanController {

	@Resource(name="loanService")
	private LoanService loanService;
	@Resource(name="bookStorageService")
	private BookStorageService bookStorageService;
	
	@GetMapping("showPopup.do")
	public ModelAndView showPopup(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/loanPopup");

		int memberNo = commandMap.getIntValue("memberNo");
		mv.addObject("memberNo", memberNo);

		List<LoanViewDTO> loanViewDTOList = loanService.getLoanListByMemberNo(memberNo);
		mv.addObject("loanViewDTOList", loanViewDTOList);
		
		return mv;
	}
	
	@PostMapping("getStoredBook.do")
	public ModelAndView getStoredBook(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/loanPopup");

		int bookNo = commandMap.getIntValue("bookNo");
		mv.addObject("bookNo", bookNo);
		int memberNo = commandMap.getIntValue("memberNo");
		mv.addObject("memberNo", memberNo);
		
		BookStorageDTO storedBook = bookStorageService.getBook(bookNo);
		mv.addObject("bookStorageDTO", storedBook);

		List<LoanViewDTO> loanViewDTOList = loanService.getLoanListByMemberNo(memberNo);
		mv.addObject("loanViewDTOList", loanViewDTOList);
		
		return mv;
	}
	
	@PostMapping("loan.do")
	public ModelAndView loan(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/loanPopup");
		
		int bookNo = commandMap.getIntValue("bookNo");
		mv.addObject("bookNo", bookNo);
		int memberNo = commandMap.getIntValue("memberNo");
		mv.addObject("memberNo", memberNo);
		boolean loanResult = loanService.loan(bookNo, memberNo);
		mv.addObject("loanResult", loanResult);
		
		List<LoanViewDTO> loanViewDTOList = loanService.getLoanListByMemberNo(memberNo);
		mv.addObject("loanViewDTOList", loanViewDTOList);
		
		return mv;
	}
}
