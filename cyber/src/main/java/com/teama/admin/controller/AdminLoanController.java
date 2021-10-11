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
import com.teama.util.Util;

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

		List<LoanViewDTO> loanViewDTOList = loanService.getViewListByMemberNo(memberNo);
		mv.addObject("loanViewDTOList", loanViewDTOList);
		
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value="searchBookAJAX.do", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String searchBookAJAX(CommandMap commandMap) {
		int bookNo = commandMap.getIntValue("bookNo");

		JSONObject jsonObj = new JSONObject();
		Map<String, Object> bookStorageViewDTO = bookStorageService.getViewMap(bookNo);
		if (bookStorageViewDTO == null) {
			jsonObj.put("errorMessage", "저장된 책이 없습니다.");
		} else {
			jsonObj.put("errorMessage", "");
			jsonObj.put("bookStorageViewDTO", bookStorageViewDTO);
		}
		
		return jsonObj.toString();
	}
	
	@PostMapping("loan.do")
	public ModelAndView loan(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/loanPopup");
		
		int bookNo = commandMap.getIntValue("bookNo");
		mv.addObject("bookNo", bookNo);
		int memberNo = commandMap.getIntValue("memberNo");
		mv.addObject("memberNo", memberNo);
		
		String errorMessage = loanService.loan(bookNo, memberNo);
		mv.addObject("errorMessage", errorMessage);
		
		List<LoanViewDTO> loanViewDTOList = loanService.getViewListByMemberNo(memberNo);
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
		
		String errorMessage = loanService.loan(bookNo, memberNo);
		jsonObj.put("errorMessage", errorMessage);
		
		List<Map<String, Object>> loanViewDTOList = loanService.getViewMapListByMemberNo(memberNo);
		for (Map<String, Object> loanViewDTO : loanViewDTOList) {
			String loanDate = Util.parseDateTime(loanViewDTO.get("loan_date"));
			loanViewDTO.put("loan_date", loanDate);
			String reserveDate = Util.parseDateTime(loanViewDTO.get("reserve_date"));
			loanViewDTO.put("reserve_date", reserveDate);
			String returnDate = Util.parseDateTime(loanViewDTO.get("return_date"));
			loanViewDTO.put("return_date", returnDate);
		}		
		jsonObj.put("loanViewDTOList", loanViewDTOList);
		
		Map<String, Object> bookStorageViewDTO = bookStorageService.getViewMap(bookNo);
		jsonObj.put("bookStorageViewDTO", bookStorageViewDTO);
		
		return jsonObj.toString();
	}
	

	@SuppressWarnings("unchecked")
	@PostMapping(value="reserveAJAX.do", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String reserveAJAX(CommandMap commandMap) {
		int bookNo = commandMap.getIntValue("bookNo");
		int memberNo = commandMap.getIntValue("memberNo");

		JSONObject jsonObj = new JSONObject();
		
		String errorMessage = loanService.reserve(bookNo, memberNo);
		jsonObj.put("errorMessage", errorMessage);
		
		List<Map<String, Object>> loanViewDTOList = loanService.getViewMapListByMemberNo(memberNo);
		for (Map<String, Object> loanViewDTO : loanViewDTOList) {
			String loanDate = Util.parseDateTime(loanViewDTO.get("loan_date"));
			loanViewDTO.put("loan_date", loanDate);
			String reserveDate = Util.parseDateTime(loanViewDTO.get("reserve_date"));
			loanViewDTO.put("reserve_date", reserveDate);
			String returnDate = Util.parseDateTime(loanViewDTO.get("return_date"));
			loanViewDTO.put("return_date", returnDate);
		}		
		jsonObj.put("loanViewDTOList", loanViewDTOList);
		
		Map<String, Object> bookStorageViewDTO = bookStorageService.getViewMap(bookNo);
		jsonObj.put("bookStorageViewDTO", bookStorageViewDTO);
		
		return jsonObj.toString();
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value="returnAJAX.do", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String returnAJAX(CommandMap commandMap) {
		int bookNo = commandMap.getIntValue("bookNo");
		int memberNo = commandMap.getIntValue("memberNo");

		JSONObject jsonObj = new JSONObject();
		
		String errorMessage = loanService.doReturn(bookNo, memberNo);
		jsonObj.put("errorMessage", errorMessage);
		
		List<Map<String, Object>> loanViewDTOList = loanService.getViewMapListByMemberNo(memberNo);
		for (Map<String, Object> loanViewDTO : loanViewDTOList) {
			String loanDate = Util.parseDateTime(loanViewDTO.get("loan_date"));
			loanViewDTO.put("loan_date", loanDate);
			String reserveDate = Util.parseDateTime(loanViewDTO.get("reserve_date"));
			loanViewDTO.put("reserve_date", reserveDate);
			String returnDate = Util.parseDateTime(loanViewDTO.get("return_date"));
			loanViewDTO.put("return_date", returnDate);
		}		
		jsonObj.put("loanViewDTOList", loanViewDTOList);
		
		Map<String, Object> bookStorageViewDTO = bookStorageService.getViewMap(bookNo);
		jsonObj.put("bookStorageViewDTO", bookStorageViewDTO);
		
		return jsonObj.toString();
	}
}
