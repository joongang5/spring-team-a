package com.teama.member.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teama.common.CommandMap;
import com.teama.member.service.MemberService;
import com.teama.util.ScriptUtil;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Resource(name = "memberService")
	private MemberService memberService;

	@GetMapping("memberJoinRegist.do")
	public String memeberJoinRegist() {
		return "member/memberJoinRegist";
	}

	@PostMapping("memberJoinRegist.do")
	public void join(CommandMap map, HttpServletResponse response) throws IOException {
		int result = memberService.join(map.getMap());

		if (result > 0)
			ScriptUtil.alertAndMovePage(response, "회원 가입에 성공했습니다.", "/cyber/index.do");
		else
			ScriptUtil.alert(response, "회원 가입에 실패했습니다.");
	}

	@PostMapping("idCheck.do")
	public @ResponseBody String idCheck(CommandMap map) {
		String id = String.valueOf(map.get("id"));
		String check = "1";
		check = memberService.isUsableId(id);
		return check;
	}

	@PostMapping("emailCheck.do")
	public @ResponseBody String emailCheck(CommandMap map) {
		String email = String.valueOf(map.get("email"));
		String check = "1";
		check = memberService.isUsableEmail(email);
		return check;
	}

	@GetMapping("memberFindId.do")
	public String memberFindId() {
		return "member/memberFindId";
	}

	@PostMapping("memberFindId.do")
	public @ResponseBody String findId(CommandMap map) {
		String email = String.valueOf(map.get("email"));
		String result = memberService.findId(email);
		return result;
	}

	@GetMapping("memberFindPw.do")
	public String memberFindPw() {
		return "member/memberFindPw";
	}

	@PostMapping("memberFindPw.do")
	public @ResponseBody String findPw(CommandMap map) {
		String id = String.valueOf(map.get("id"));
		String result = memberService.findPw(id);
		return result;
	}
}