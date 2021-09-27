package com.teama.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.member.dao.MemberDAO;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private MemberDAO memberDAO;
}
