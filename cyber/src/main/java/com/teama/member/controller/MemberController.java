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
import com.teama.member.service.LoginService;
import com.teama.member.service.MemberService;
import com.teama.util.ScriptUtil;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Resource(name = "memberService")
	private MemberService memberService;

	@Resource(name = "loginService")
	private LoginService loginService;
	
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

	public static String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		// System.out.println("charSet.length :::: "+charSet.length);
		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거)
			// System.out.println("idx :::: "+idx);
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}

	@PostMapping("memberFindPw.do")
	public @ResponseBody String findPw(CommandMap map) {
		String id = String.valueOf(map.get("id"));
		String pw = getRamdomPassword(10);
		System.out.println(pw);
		String salt = loginService.getSalt(id);
		map.put("pw", pw);
		map.put("salt", salt);
		memberService.updatePw(map.getMap());
		System.out.println("::::::::::::::::::::::::::::::::::::"+map.getMap());
		return pw;
	}
}