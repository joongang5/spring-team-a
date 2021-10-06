package com.teama.member.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teama.member.service.MyPageService;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
	@Resource(name="myPageService")
	private MyPageService myPageService;
	
	@GetMapping("myPage.do")
	private String myPage() {
		return "member/myPage";
	}
}
