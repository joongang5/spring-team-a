package com.teama.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.teama.member.dto.MemberDTO;

@Service
public interface MemberService {

	public void join(Map<String, Object> map);

	public String isUsableId(String id);
	
	public String isUsableEmail(String email);

	public MemberDTO getMemberByNo(int memberNo);

	public List<MemberDTO> getMemberList();

	public List<MemberDTO> getMemberList(String searchType, String searchValue);

	public List<MemberDTO> getMemberListByNo(int memberNo);

}