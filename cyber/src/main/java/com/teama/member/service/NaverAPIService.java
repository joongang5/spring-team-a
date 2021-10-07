package com.teama.member.service;

public class NaverAPIService {
	private final String CLIENT_ID = "wn_m3HL4uolGFA5Fudbr";
	private final String CLIENT_SECRET = "VPT8CIEF4K";
	private final String BASE_URL_AUTH = "https://nid.naver.com/oauth2.0/authorize";
	private final String BASE_URL_TOKEN = "https://nid.naver.com/oauth2.0/token";
	private final String REDIRECT_URI_AUTH = "http://localhost:8080/cyber/onNaverLoginCallback.do";
}