package com.teama.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.member.dto.MemberDTO;
import com.teama.member.service.MemberService;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

	@Resource(name="memberService")
	private MemberService memberService;
	
	@GetMapping("home.do")
	public ModelAndView home(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("admin/member");
		
		List<MemberDTO> memberDTOList = memberService.getMemberList();
		mv.addObject("memberDTOList", memberDTOList);
		
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
}
