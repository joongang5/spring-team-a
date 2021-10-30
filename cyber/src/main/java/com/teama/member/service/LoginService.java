package com.teama.member.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface LoginService {

	public Map<String, Object> login(HttpSession session, Map<String, Object> map);

	public String getSalt(String id);
}