package com.teama.admin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.loan.dto.LoanViewDTO;
import com.teama.loan.service.LoanService;
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
	
	@PostMapping(value="searchBookAJAX.do", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String searchBookAJAX(CommandMap commandMap) {
		int bookNo = commandMap.getIntValue("bookNo");

		Map<String, Object> bookStorageViewDTO = bookStorageService.getBookMap(bookNo);

		JSONObject jsonObj = new JSONObject(bookStorageViewDTO);
		
		return jsonObj.toString();
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
	
	@SuppressWarnings("unchecked")
	@PostMapping(value="loanAJAX.do", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String loanAJAX(CommandMap commandMap) {
		int bookNo = commandMap.getIntValue("bookNo");
		int memberNo = commandMap.getIntValue("memberNo");

		JSONObject jsonObj = new JSONObject();
		
		boolean loanResult = loanService.loan(bookNo, memberNo);
		jsonObj.put("loanResult", loanResult);
		
		List<Map<String, Object>> loanViewDTOList = loanService.getLoanMapListByMemberNo(memberNo);
		for (Map<String, Object> loanViewDTO : loanViewDTOList) {
			Object loanDateObj = loanViewDTO.get("loan_date");
			loanViewDTO.put("loan_date", loanDateObj.toString());
			Object returnDateObj = loanViewDTO.get("return_date");
			loanViewDTO.put("return_date", returnDateObj.toString());
		}
		
		jsonObj.put("loanViewDTOList", loanViewDTOList);
		
		return jsonObj.toString();
	}
}
