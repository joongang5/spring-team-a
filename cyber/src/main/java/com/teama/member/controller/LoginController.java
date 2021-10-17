package com.teama.member.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teama.common.CommandMap;
import com.teama.member.dto.MemberDTO;
import com.teama.member.service.LoginAPIService;
import com.teama.member.service.LoginService;
import com.teama.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class LoginController {

	@Resource(name = "loginService")
	private LoginService loginService;

	@Resource(name = "naverAPIService")
	private LoginAPIService naverAPIService;

	@Resource(name = "memberService")
	private MemberService memberService;
	
	// @Resource(name = "kakaoAPIService")
	// private LoginAPIService kakaoAPIService;

	@GetMapping("memberLogin.do")
	public String login() {
		return "member/memberLogin";
	}
	
	@PostMapping(value="memberLogin.do", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String login(CommandMap commandMap, HttpServletRequest request) {
		String errorMessage = "";
		
		String id = commandMap.getStrValue("id");
		String pw = commandMap.getStrValue("pw");
		if (id.isEmpty())
			errorMessage = "아이디를 입력해주세요.";
		else if (pw.isEmpty())
			errorMessage = "비밀번호를 입력해주세요.";
		
		if (errorMessage.isEmpty()) {
			Map<String, Object> login = loginService.login(commandMap.getMap());
			if (login != null) {
				HttpSession session = request.getSession();
				session.setAttribute("name", login.get("name"));
				session.setAttribute("id", login.get("id"));
				session.setAttribute("memberNo", login.get("no"));
				session.setAttribute("grade", login.get("grade")); //1013 소영 grade 세션 추가	
			} else {
				errorMessage = "일치하는 회원정보가 없습니다.";
			}
		}

		return errorMessage;
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

	@PostMapping("naverAuth.do")
	public String naverAuth(HttpServletRequest request) throws UnsupportedEncodingException {
		return "redirect:" + naverAPIService.requestAuth(request);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("onNaverLoginCallback.do")
	public String onNaverLoginCallback(HttpServletRequest request, @RequestParam Map<String, String> params)
			throws JsonMappingException, JsonProcessingException {
		String code = params.get("code");
		String state = params.get("state");
		String storedState = (String) request.getSession().getAttribute("state");

		if (state.equals(storedState)) {
			MemberDTO memberDTO = naverAPIService.requestToken(code, state);
			
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> memberDTOMap = mapper.convertValue(memberDTO, Map.class);
			
			memberService.join(memberDTOMap);
		}

		return "redirect:/index.do";
	}
}