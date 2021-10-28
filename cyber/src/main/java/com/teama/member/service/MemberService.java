package com.teama.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.teama.common.CommandMap;
import com.teama.member.dto.MemberDTO;

@Service
public interface MemberService {

	public int join(Map<String, Object> map);

	public String isUsableId(String id);

	public String isUsableEmail(String email);

	public int getTotalCount();

	public int todayJoinCount();

	public String findId(String email);

	public String findPw(String id);

	public int updatePw(Map<String, Object> map);

	public MemberDTO getMemberByNo(int memberNo);

	public MemberDTO getMemberById(String memberId);

	public List<MemberDTO> getMemberList();

	public List<MemberDTO> getMemberList(int firstIndex, int recordCountPerPage);

	public List<MemberDTO> getRecentlyMemberList(int limitCount);

	public List<MemberDTO> getMemberList(String searchType, String searchValue);

	public List<MemberDTO> getMemberListByNo(int memberNo);

	public int memberUpdate(MemberDTO memberDTO);

	public String encryptPw(Map<String, Object> map);

}