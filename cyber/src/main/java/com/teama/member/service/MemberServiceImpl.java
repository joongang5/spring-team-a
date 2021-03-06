package com.teama.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.member.dao.MemberDAO;
import com.teama.member.dto.MemberDTO;
import com.teama.util.Util;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public int join(Map<String, Object> map) {
		map.put("salt", Util.generateSalt());
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
	public int getTotalCount() {
		return memberDAO.getTotalCount();
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
	public int updatePw(Map<String, Object> map) {
		return memberDAO.updatePw(map);
	}

	@Override
	public MemberDTO getMemberByNo(int memberNo) {
		return memberDAO.getMemberByNo(memberNo);
	}

	@Override
	public MemberDTO getMemberById(String id) {
		return memberDAO.getMemberById(id);
	}

	@Override
	public List<MemberDTO> getMemberList() {
		return memberDAO.getMemberList();
	}

	@Override
	public List<MemberDTO> getMemberList(int firstIndex, int recordCountPerPage) {
		return memberDAO.getMemberList(firstIndex, recordCountPerPage);
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

	@Override
	public int memberUpdate(MemberDTO memberDTO) {
		return memberDAO.memberUpdate(memberDTO);
	}

	@Override
	public String encryptPw(Map<String, Object> map) {
		return memberDAO.encryptPw(map);
	}
}