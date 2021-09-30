package com.teama.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String join(HttpServletRequest request) {

		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("id", id);
		map.put("pw", pw);
		map.put("email", email);

		MemberService.join(map);

		return "redirect:index.do";
	}
}