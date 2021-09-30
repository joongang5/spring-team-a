package com.teama.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.member.dao.MemberDAO;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private MemberDAO memberDAO;

	@Override
	public Map<String, Object> login(Map<String, Object> map) {
		return memberDAO.login(map);
	}
}