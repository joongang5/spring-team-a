package com.teama.member.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teama.common.CommandMap;
import com.teama.member.service.MemberService;

@Controller
public class MemberController {

	@Resource(name = "memberService")
	private MemberService memberService;

	@GetMapping("memberJoinRegist.do")
	public String memeberJoinRegist() {
		return "member/memberJoinRegist";
	}

	@PostMapping("memberJoinRegist.do")
	public String join(CommandMap map) {
		String name = String.valueOf(map.get("name"));
		String id = String.valueOf(map.get("value"));
		;
		String pw = String.valueOf(map.get("pw"));
		;
		String email = String.valueOf(map.get("email"));
		;

		memberService.join(map.getMap());

		return "redirect:index.do";
	}

	@ResponseBody
	@PostMapping("idCheck.do")
	public boolean idCheck(CommandMap map) {
		boolean check = memberService.isUsableId(map.getMap());
		System.out.println("오는 값 ㅣ: " + check);
		return check;
	}
}