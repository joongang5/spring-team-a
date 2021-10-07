package com.teama.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.ebook.dto.EbookDTO;
import com.teama.ebook.service.EbookService;
import com.teama.loan.dto.LoanViewDTO;
import com.teama.loan.service.LoanService;
import com.teama.member.service.MyPageService;
import com.teama.util.Util;

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
	private ModelAndView myPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("member/myPage");
		
		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = Util.parseInt(memberNoObj);

			List<LoanViewDTO> loanViewDTOList = loanService.getLoanListByMemberNo(memberNo);
			mv.addObject("loanViewDTOList", loanViewDTOList);
		}
		return mv;
	}
}
