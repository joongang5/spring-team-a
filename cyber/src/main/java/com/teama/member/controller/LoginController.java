package com.teama.member.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.teama.common.CommandMap;
import com.teama.member.service.LoginService;

@Controller
public class LoginController {

	@Resource(name = "loginService")
	private LoginService loginService;

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

		return "redirect:index.do";
	}

	@GetMapping("/logout.do")
	public String logout(CommandMap commandMap, HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (session.getAttribute("name") != null) {
			session.removeAttribute("name");
		}

		if (session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}

		return "redirect:index.do";
	}
}