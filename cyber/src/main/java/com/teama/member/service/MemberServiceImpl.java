package com.teama.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.member.dao.MemberDAO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public void join(Map<String, Object> map) {
		memberDAO.join(map);
	}

	@Override
	public boolean isUsableId(Map<String, Object> map) {
		boolean result = false;
		try {
			result = memberDAO.isUsableId(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}