package com.teama.member.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.teama.member.service.LoginService;

@Controller
public class LoginController {

	@Resource(name="loginService")
	private LoginService loginService;
	
	@GetMapping("memberLogin.do")
	public String memberLogin() {
		return "member/memberLogin";
	}
}
