package com.teama.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.service.IndexService;

@Controller
public class IndexController {

	@Resource(name="indexService")
	private IndexService indexService;
	
	@GetMapping("index.do")
	public String main() throws Exception {
		return "index";
	}
}
