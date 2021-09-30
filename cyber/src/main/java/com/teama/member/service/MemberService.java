package com.teama.member.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {

	public void join(Map<String, Object> map);

	public boolean isUsableId(Map<String, Object> map);
}