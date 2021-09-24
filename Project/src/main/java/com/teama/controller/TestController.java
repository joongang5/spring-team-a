package com.teama.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.service.TestService;

@Controller
public class TestController {

	@Resource(name="testService")
	private TestService testService;
	
	@GetMapping("main.do")
	public ModelAndView main() throws Exception {
		ModelAndView mv = new ModelAndView("main");
		List<Map<String, Object>> list = testService.boardList();
		mv.addObject("list", list);
		return mv;
	}
	
	@GetMapping("write.do")
	public ModelAndView write() throws Exception {
		ModelAndView mv = new ModelAndView("write");
		return mv;
	}

	@PostMapping("write.do")
	public ModelAndView write(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("");
		System.out.println(commandMap.getMap());
		return mv;
	}
}
