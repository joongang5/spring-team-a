package com.teama.member.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.teama.member.dto.MemberDTO;

public abstract interface LoginAPIService {

	public String requestAuth(HttpServletRequest request) throws UnsupportedEncodingException;

	public MemberDTO requestToken(String code, String state) throws JsonMappingException, JsonProcessingException;

}
