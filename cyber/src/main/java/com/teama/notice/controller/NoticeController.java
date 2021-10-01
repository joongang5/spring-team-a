package com.teama.notice.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.notice.service.NoticeServiceImpl;


@Controller
public class NoticeController {

	@Resource(name="noticeService")
	private NoticeServiceImpl noticeService;
	
	//게시글 불러오기, 페이징
	@RequestMapping("/listNotice.do")
	public ModelAndView noticeList() {
		ModelAndView mv = new ModelAndView("notice/listNotice");
		List<Map<String, Object>> list = noticeService.noticeList();
		mv.addObject("list", list);
		return mv;
	}
	
	//게시글 상세보기
	@RequestMapping("/noticeDetail.do")
	public ModelAndView detail(CommandMap map) {
		ModelAndView mv = new ModelAndView("notice/noticeDetail");
		Map<String, Object> detail = noticeService.detail(map.getMap());
		mv.addObject("detail", detail);
		return mv;
	}
	
	//게시글 글쓰기
	
	//게시글 삭제
	@RequestMapping("/delete.do")
	public String delete(CommandMap map) {
		int result = noticeService.delete(map.getMap());
		System.out.println("delete = " + result);

		return "redirect:listNotice.do";
	}
	
	//게시글 수정
	
}
