package com.teama.member.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teama.common.CommandMap;
import com.teama.member.service.LoginAPIService;
import com.teama.member.service.LoginService;
import com.teama.member.service.NaverAPIServiceImpl;

@Controller
@RequestMapping("/member")
public class LoginController {

	@Resource(name = "loginService")
	private LoginService loginService;
	@Resource(name = "naverAPIService")
	private LoginAPIService naverAPIService;
	//@Resource(name = "kakaoAPIService")
	//private LoginAPIService kakaoAPIService;

	@GetMapping("memberLogin.do")
	public String login() {
		return "member/memberLogin";
	}

	@PostMapping("memberLogin.do")
	public String login(CommandMap commandMap, HttpServletRequest request) {
		Map<String, Object> login = loginService.login(commandMap.getMap());
		HttpSession session = request.getSession();
		session.setAttribute("name", login.get("name"));
		session.setAttribute("id", login.get("id"));
		session.setAttribute("memberNo", login.get("no"));

		return "redirect:/index.do";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}

		if (session.getAttribute("name") != null) {
			session.removeAttribute("name");
		}
		return "redirect:/index.do";
	}

	@GetMapping("naverAuth.do")
	public String naverAuth() {
		return "member/memberLogin";
	}

	@GetMapping("onNaverLoginCallback.do")
	public String onNaverLoginCallback() {
		return "member/memberLogin";
	}
}