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
import com.teama.common.CommandMap;
import com.teama.member.dto.MemberDTO;
import com.teama.member.service.KakaoAPIServiceImpl;
import com.teama.member.service.LoginService;
import com.teama.member.service.MemberService;
import com.teama.member.service.NaverAPIServiceImpl;
import com.teama.util.Util;

@Controller
@RequestMapping("/member")
public class LoginController {

	@Resource(name = "loginService")
	private LoginService loginService;

	@Resource(name = "naverAPIService")
	private NaverAPIServiceImpl naverAPIService;

	@Resource(name = "kakaoAPIService")
	private KakaoAPIServiceImpl kakaoAPIService;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
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
			// loginViewDTO 클래스는 존재하지 않지만 로그인 결과 값이 무엇인지 명시하기 위해 사용
			//salt 값 가져오기
			String salt = loginService.getSalt(id);
			commandMap.put("salt", salt);
			Map<String, Object> loginViewDTO = loginService.login(commandMap.getMap());
			if (loginViewDTO != null) {
				HttpSession session = request.getSession();
				session.setAttribute("name", loginViewDTO.get("name"));
				session.setAttribute("id", loginViewDTO.get("id"));
				session.setAttribute("memberNo", loginViewDTO.get("no"));
				session.setAttribute("grade", loginViewDTO.get("grade")); //1013 소영 grade 세션 추가
				session.setAttribute("platform", loginViewDTO.get("platform"));
			} else {
				errorMessage = "일치하는 회원정보가 없습니다.";
			}
		}

		return errorMessage;
	}

	@GetMapping("/logout.do")
	public String logout(HttpServletRequest request) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		HttpSession session = request.getSession();

		// 세션에 로그인 관련 정보가 남아있다면 로그인 상태이다.
		if (session.getAttribute("id") != null) {
			// 기본 로그인 정보를 제거한다.
			session.removeAttribute("id");
			session.removeAttribute("name");
			session.removeAttribute("memberNo");
			session.removeAttribute("grade"); //1013 소영 grade 세션 추가

			Object platformObj = session.getAttribute("platform");
			int platform = Util.parseInt(platformObj);
			session.removeAttribute("platform");
			
			if (platform == 1) {
				Object accessTokenObj = session.getAttribute("accessToken");
				String accessToken = String.valueOf(accessTokenObj);
				naverAPIService.requestLogout(accessToken);
				session.removeAttribute("accessToken"); 
			} else if (platform == 2) {
				return "redirect:" + kakaoAPIService.requestLogoutAuth();
			}
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

	@GetMapping("onNaverLoginCallback.do")
	public String onNaverLoginCallback(HttpServletRequest request, @RequestParam Map<String, String> params)
			throws JsonMappingException, JsonProcessingException {
		String code = params.get("code");
		String state = params.get("state");
		String storedState = (String) request.getSession().getAttribute("state");

		if (state.equals(storedState)) {
			String accessToken = naverAPIService.requestToken(code, state);
			
			Map<String, Object> profileMap = naverAPIService.requestProfile(accessToken);
			
			String id = String.valueOf(profileMap.get("id"));
			MemberDTO memberDTO = memberService.getMemberById(id);

			if (memberDTO == null) {
				// 회원 정보가 없으면
				memberService.join(profileMap);
				
				memberDTO = memberService.getMemberById(id);
			}
			
			if (memberDTO != null) {
				HttpSession session = request.getSession();
				session.setAttribute("name", memberDTO.getName());
				session.setAttribute("id", memberDTO.getId());
				session.setAttribute("memberNo", memberDTO.getNo());
				session.setAttribute("grade", memberDTO.getGrade());
				session.setAttribute("platform", memberDTO.getPlatform());
				session.setAttribute("accessToken", accessToken);
			}
		}

		return "redirect:/index.do";
	}
	
	@PostMapping("kakaoAuth.do")
	public String kakaoAuth(HttpServletRequest request) throws UnsupportedEncodingException {
		return "redirect:" + kakaoAPIService.requestAuth(request);
	}
	
	@GetMapping("onKakaoLoginCallback.do")
	public String onKakaoLoginCallback(@RequestParam Map<String, String> params, HttpServletRequest request)
			throws JsonMappingException, JsonProcessingException {
		String code = params.get("code");

		String accessToken = kakaoAPIService.requestToken(code);

		Map<String, Object> profileMap = kakaoAPIService.requestProfile(accessToken);
		System.out.println("컨트롤러 출력" + profileMap.get("id"));
		
		// 회원 정보가 있으면
		// 로그인처리 (session에 등록)
		String id = String.valueOf(profileMap.get("id"));
		MemberDTO memberDTO = memberService.getMemberById(id);
		
		if (memberDTO == null) {
			// 회원 정보가 없으면
			memberService.join(profileMap);
			
			memberDTO = memberService.getMemberById(id);
		}
		
		if (memberDTO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("name", memberDTO.getName());
			session.setAttribute("id", memberDTO.getId());
			session.setAttribute("memberNo", memberDTO.getNo());
			session.setAttribute("grade", memberDTO.getGrade());
			session.setAttribute("platform", memberDTO.getPlatform());
		}
		
		return "redirect:/index.do";
	}
	
	@PostMapping("kakaoLogoutAuth.do")
    public String kakaoLogoutAuth(HttpServletRequest request) throws UnsupportedEncodingException {
        return "redirect:" + kakaoAPIService.requestLogoutAuth();
    }
	
	@GetMapping("onKakaoLogout.do")
	public String onKakaoLogout(@RequestParam Map<String, String> params, HttpServletRequest request) 
			throws JsonMappingException, JsonProcessingException {
		
		return "redirect:/index.do";
	}
}