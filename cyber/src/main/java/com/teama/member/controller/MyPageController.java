package com.teama.member.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.teama.common.CommandMap;
import com.teama.ebook.dto.EbookReviewDTO;
import com.teama.ebook.service.EbookService;
import com.teama.loan.dto.LoanDTO;
import com.teama.loan.dto.LoanViewDTO;
import com.teama.loan.service.LoanService;
import com.teama.member.dto.MemberDTO;
import com.teama.member.service.LoginService;
import com.teama.member.service.MemberService;
import com.teama.member.service.MyPageService;
import com.teama.util.ScriptUtil;
import com.teama.util.Util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
	@Resource(name="myPageService")
	private MyPageService myPageService;
	@Resource(name="loanService")
	private LoanService loanService;
	@Resource(name="ebookService")
	private EbookService ebookService;
	@Resource(name = "memberService")
	private MemberService memberService;
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@GetMapping("myPage.do")
	private ModelAndView myPage(HttpServletRequest request, CommandMap map) {
		ModelAndView mv = new ModelAndView("member/myPage");
		
		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = Util.parseInt(memberNoObj);
			map.put("memberNo", memberNo);
			int ltotalCount = loanService.ltotalCount(map.getMap());
			int reserveCount = loanService.reserveCount(map.getMap());
			
			List<LoanViewDTO> loanViewList = loanService.getViewListByMemberNo(map.getMap());
			
			mv.addObject("loanViewList", loanViewList);
			mv.addObject("ltotalCount", ltotalCount);
			mv.addObject("reserveCount", reserveCount);
		}
		return mv;
	}
	
	@GetMapping("ebookLoanList.do")
	private ModelAndView ebookLoanList(HttpServletRequest request, CommandMap map) {
		ModelAndView mv = new ModelAndView("ebook/ebookLoanList");
		
		if(request.getSession().getAttribute("id")==null) {
			mv.setViewName("redirect:/member/memberLogin.do");
			return mv;
		}else {			
	
		int pageNo = 1;
		
		if (map.containsKey("pageNo")) {
			pageNo= Integer.parseInt(String.valueOf(map.get("pageNo")));
		}
		int listScale = 4;
		int pageScale = 10;

		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = Util.parseInt(memberNoObj);
			map.put("memberNo", memberNo);
			int totalCount = loanService.totalCount(map.getMap());
			//System.out.println(totalCount + "개가 있습니다.");
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(pageNo);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			paginationInfo.setTotalRecordCount(totalCount);//
			
			int startPage = paginationInfo.getFirstRecordIndex();
			int lastPage = paginationInfo.getRecordCountPerPage();
			
			//System.out.println(startPage);
			//System.out.println(lastPage);
			
			map.put("startPage", startPage);
			map.put("lastPage", lastPage);
			
			List<LoanViewDTO> loanViewDTOList = loanService.getViewPagingListByMemberNo(map.getMap());
			
			mv.addObject("loanViewDTOList", loanViewDTOList);
			mv.addObject("paginationInfo", paginationInfo);
			mv.addObject("pageNo", pageNo);
			mv.addObject("totalCount", totalCount);
			
		}
		//System.out.println("last Map : "+map.getMap());
		return mv;
		}
	}

	@PostMapping(value="doReturn.do", produces="text/plain;charset=utf-8")
	@ResponseBody
	private String doReturn(HttpServletRequest request, CommandMap map) {
		String errorMessage = "";
		
		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj == null) {
			errorMessage = "세션이 만료되었습니다."; 
			return errorMessage;
		}
		else {
			int no = map.getIntValue("no");
			errorMessage = loanService.doReturn(no);
		}
		
		return errorMessage;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value="doExtend.do", produces="text/plain;charset=utf-8")
	@ResponseBody
	private String doExtend(HttpServletRequest request, CommandMap map) {
		String errorMessage = "";
		String redate = "";
		
		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj == null) {
			errorMessage = "세션이 만료되었습니다."; 
			return errorMessage;			
		} else {
			int no = map.getIntValue("no");
			errorMessage = loanService.doExtend(no);
			
			if (errorMessage.isEmpty()) {
				LoanDTO loanDTO = loanService.getLoan(no);
				if (loanDTO == null) {
					errorMessage = "대출 정보를 찾을 수 없습니다.";
				} else {
					redate = loanDTO.getReturn_date();
				}
			}
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("errorMessage", errorMessage);
		jsonObj.put("redate", redate);
		
		return jsonObj.toString();
	}

	@GetMapping("memberModify.do")
	public String memberModify(HttpServletRequest request) {
		String viewPath;
		
		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj == null) {
			viewPath = "redirect:/member/memberLogin.do";
		} else {
			viewPath = "member/pwCheck";
		}
		
		return viewPath;
	}

	@PostMapping("memberModify.do")
	public ModelAndView memberModify(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = Util.parseInt(memberNoObj);
			
			MemberDTO memberDTO = memberService.getMemberByNo(memberNo);
			//salt 값 가져오기
			String salt = loginService.getSalt(memberDTO.getId());
			commandMap.put("salt", salt);
			//비밀번호 암호화 하여 비교
			System.out.println(commandMap.getMap().get("salt"));
			String pw = memberService.encryptPw(commandMap.getMap());
			if (pw.equals(memberDTO.getPw())) {
				mv.addObject("memberDTO", memberDTO);
				mv.setViewName("member/memberModify");
			} else {
				ScriptUtil.alert(response, "비밀번호가 일치하지 않습니다.");
				mv.setViewName("member/pwCheck");
			}
		}
		return mv;
	}
	
	@PostMapping("memberUpdate.do")
	public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap) throws IOException {
		ModelAndView mv = new ModelAndView("member/pwCheck");
		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = Util.parseInt(memberNoObj);
			
			MemberDTO memberDTO = memberService.getMemberByNo(memberNo);
			
			String pw = commandMap.getStrValue("pw");
			memberDTO.setPw(pw);
			String email = commandMap.getStrValue("email");
			memberDTO.setEmail(email);
			
			memberService.memberUpdate(memberDTO);
			
			ScriptUtil.alert(response, "정보 수정에 성공했습니다.");
		}
		return mv;
	}
	
	@GetMapping("ebookReviewList.do")
	private ModelAndView ebookReviewList(HttpServletRequest request, CommandMap map) {
		ModelAndView mv = new ModelAndView("ebook/ebookReviewList");
		
		int pageNo = 1;
		
		if (map.containsKey("pageNo")) {
			pageNo= Integer.parseInt(String.valueOf(map.get("pageNo")));
		}
		int listScale = 4;
		int pageScale = 10;

		HttpSession session = request.getSession();
		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = Util.parseInt(memberNoObj);
			map.put("memberNo", memberNo);
			List<EbookReviewDTO> reviewDTOList = ebookService.getReviewListByMemberNo(map.getMap());
			if(reviewDTOList.get(0).getReviewCount() != 0) {
				int totalCount = reviewDTOList.get(0).getReviewCount();
				System.out.println(totalCount);
				PaginationInfo paginationInfo = new PaginationInfo();
				
				paginationInfo.setCurrentPageNo(pageNo);
				paginationInfo.setRecordCountPerPage(listScale);
				paginationInfo.setPageSize(pageScale);
				paginationInfo.setTotalRecordCount(totalCount);//
				
				int startPage = paginationInfo.getFirstRecordIndex();
				int lastPage = paginationInfo.getRecordCountPerPage();
				
				//System.out.println(startPage);
				//System.out.println(lastPage);
				
				map.put("startPage", startPage);
				map.put("lastPage", lastPage);
				
				mv.addObject("reviewDTOList", reviewDTOList);
				mv.addObject("paginationInfo", paginationInfo);
				mv.addObject("pageNo", pageNo);
				mv.addObject("totalCount", totalCount);
				
			}
			
		}
		//System.out.println("last Map : "+map.getMap());
		return mv;
	}
}
