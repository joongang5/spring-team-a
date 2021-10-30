package com.teama.member.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.member.dao.MemberDAO;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private MemberDAO memberDAO;

	@Override
	public Map<String, Object> login(HttpSession session, Map<String, Object> map) {
		Map<String, Object> loginViewDTO = memberDAO.login(map);
		
		session.setAttribute("name", loginViewDTO.get("name"));
		session.setAttribute("id", loginViewDTO.get("id"));
		session.setAttribute("memberNo", loginViewDTO.get("no"));
		session.setAttribute("grade", loginViewDTO.get("grade")); //1013 소영 grade 세션 추가
		session.setAttribute("platform", loginViewDTO.get("platform"));
		
		return loginViewDTO;
	}

	@Override
	public String getSalt(String id) {
		return memberDAO.getSalt(id);
	}
}