package com.teama.member.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.teama.member.service.MemberService;

@Controller
public class MemberController {

	@Resource(name="memberService")
	private MemberService memberService;
	
	@GetMapping("memberJoinRegist.do")
	public String memberJoinRegist() {
		return "member/memberJoinRegist";
	}
}
