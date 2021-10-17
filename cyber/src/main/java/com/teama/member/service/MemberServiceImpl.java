package com.teama.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.member.dao.MemberDAO;
import com.teama.member.dto.MemberDTO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public int join(Map<String, Object> map) {
		return memberDAO.join(map);
	}

	@Override
	public String isUsableId(String id) {
		return memberDAO.isUsableId(id);
	}

	@Override
	public String isUsableEmail(String email) {
		return memberDAO.isUsableEmail(email);
	}

	@Override
	public int todayJoinCount() {
		return memberDAO.todayJoinCount();
	}
	
	@Override
	public String findId(String email) {
		return memberDAO.findId(email);
	}
	
	@Override
	public String findPw(String id) {
		return memberDAO.findPw(id);
	}

	@Override
	public MemberDTO getMemberByNo(int memberNo) {
		return memberDAO.getMemberByNo(memberNo);
	}

	@Override
	public List<MemberDTO> getMemberList() {
		return memberDAO.getMemberList();
	}

	@Override
	public List<MemberDTO> getRecentlyMemberList(int limitCount) {
		return memberDAO.getRecentlyMemberList(limitCount);
	}

	@Override
	public List<MemberDTO> getMemberListByNo(int memberNo) {
		return memberDAO.getMemberListByNo(memberNo);
	}

	@Override
	public List<MemberDTO> getMemberList(String searchType, String searchValue) {
		List<MemberDTO> result = null;

		if (searchType.equals("id")) {
			result = memberDAO.memberListById(searchValue);
		} else if (searchType.equals("name")) {
			result = memberDAO.memberListByName(searchValue);
		} else if (searchType.equals("email")) {
			result = memberDAO.memberListByEmail(searchValue);
		} else if (searchType.equals("join_date")) {
			result = memberDAO.memberListByJoinDate(searchValue);
		}

		return result;
	}
}