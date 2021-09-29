package com.teama.notice.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	@GetMapping("listNotice.do")
	public String listNotice() {
		return "notice/listNotice";
	}
	
	@RequestMapping(value="/listNotice.do")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("listNotice");
		List<Map<String, Object>> list = noticeService.list();
		mv.addObject("list",list);
		System.out.println(list);
		return mv;
	}
	
	@RequestMapping("/noticeDetail.do")
	public ModelAndView detail(CommandMap map) {
		ModelAndView mv = new ModelAndView("noticeDetail");
		Map<String, Object> detail = noticeService.detail(map.getMap());
		mv.addObject("detail",detail);
		return mv;
	}
}
