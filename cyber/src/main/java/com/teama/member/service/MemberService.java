package com.teama.member.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.teama.member.dao.MemberDAO;

@Service
public interface MemberService {

	static int join(Map<String, Object> map) {
		return MemberDAO.join(map);
	}
}