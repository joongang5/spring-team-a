package com.teama.member.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teama.api.util.HttpURLConnUtil;

@Service("kakaoAPIService")
public class KakaoAPIServiceImpl {
	
	private final String CLIENT_ID = "c782430ad89e54421f7ab9c95375c500"; 
	private final String REDIRECT_URI = "http://localhost:8080/cyber/member/onKakaoLoginCallback.do";
	private final String BASE_URL_TOKEN = "https://kauth.kakao.com/oauth/token";
	private final String BASE_URL_PROFILE = "https://kapi.kakao.com/v2/user/me";
	private final String KAKAO_LOGOUT_URL = "https://kauth.kakao.com/oauth/logout";
	private final String LOGOUT_REDIRECT_URI = "http://localhost:8080/cyber/member/onKakaoLogout.do";
	
	public String requestAuth(HttpServletRequest request) throws UnsupportedEncodingException {
		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?"
			+ "client_id=" + CLIENT_ID + "&redirect_uri="
			+ REDIRECT_URI + "&response_type=code";
		return kakaoUrl;
	}

	@SuppressWarnings("unchecked")
	public String requestToken(String code) throws JsonMappingException, JsonProcessingException {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("client_id", CLIENT_ID);
		params.put("redirect_uri", REDIRECT_URI);
		params.put("grant_type", "authorization_code");
		params.put("code", code);

		String tokenResponse = HttpURLConnUtil.doPostRequest(BASE_URL_TOKEN, null, params);
		
		ObjectMapper tokenMapper = new ObjectMapper();
		Map<String, String> tokenMap = tokenMapper.readValue(tokenResponse, Map.class);
		String accessToken = tokenMap.get("access_token");
//		String refreshToken = tokenMap.get("refresh_token");
//		String tokenType = tokenMap.get("token_type");
//		String expiresIn = tokenMap.get("expires_in");
		
		return accessToken;
	}

	public Map<String, Object> requestProfile(String accessToken) throws JsonMappingException, JsonProcessingException {
		String header = "Bearer " + accessToken;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", header);

		String profileResponse = HttpURLConnUtil.doGetRequest(BASE_URL_PROFILE, headers, null);

		ObjectMapper profileMapper = new ObjectMapper();
		JsonNode rootNode = profileMapper.readTree(profileResponse);
		JsonNode propertiesNode = rootNode.get("properties");
		JsonNode kakaoAccountNode = rootNode.get("kakao_account");
		String id = getStrValueFromJsonNode(rootNode, "id");
		String email = getStrValueFromJsonNode(kakaoAccountNode, "email");
		String nickname = getStrValueFromJsonNode(propertiesNode, "nickname");
		
		Map<String, Object> profileMap = new HashMap<String, Object>();
		profileMap.put("id", id);
		profileMap.put("pw", "");
		profileMap.put("email", email);
		profileMap.put("name", nickname);
		profileMap.put("platform", 2);
		
		return profileMap;
	}

	public String requestLogoutAuth() throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("client_id", CLIENT_ID);
		params.put("logout_redirect_uri", LOGOUT_REDIRECT_URI);
		String strParams = HttpURLConnUtil.parseParams(params);
		
		return KAKAO_LOGOUT_URL + "?" + strParams;
	}
	
	public String requestLogoutToken(String code) throws JsonMappingException, JsonProcessingException {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("client_id", CLIENT_ID);
		params.put("logout_redirect_uri", LOGOUT_REDIRECT_URI);

		String logoutTokenResponse = HttpURLConnUtil.doPostRequest(BASE_URL_TOKEN, null, params);
		
		ObjectMapper tokenMapper = new ObjectMapper();
		Map<String, String> tokenMap = tokenMapper.readValue(logoutTokenResponse, Map.class);
		String accessToken = tokenMap.get("access_token");
//		String refreshToken = tokenMap.get("refresh_token");
//		String tokenType = tokenMap.get("token_type");
//		String expiresIn = tokenMap.get("expires_in");
		
		return accessToken;
	}
	
	public Map<String, Object> requestLogout(String accessToken) throws JsonMappingException, JsonProcessingException {
		String header = "Bearer " + accessToken;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", header);
		
		String logoutResponse = HttpURLConnUtil.doPostRequest(LOGOUT_REDIRECT_URI, headers, headers);
		
		ObjectMapper logoutMapper = new ObjectMapper();
		JsonNode rootNode = logoutMapper.readTree(logoutResponse);
		JsonNode propertiesNode = rootNode.get("properties");
		JsonNode kakaoAccountNode = rootNode.get("kakao_account");
		String id = getStrValueFromJsonNode(rootNode, "id");
		String email = getStrValueFromJsonNode(kakaoAccountNode, "email");
		String nickname = getStrValueFromJsonNode(propertiesNode, "nickname");
		
		Map<String, Object> logoutMap = new HashMap<String, Object>();
		logoutMap.put("id", id);
		logoutMap.put("email", email);
		logoutMap.put("name", nickname);
		
		return logoutMap;
	}
	
	private String getStrValueFromJsonNode(JsonNode parentNode, String key) {
		JsonNode node = parentNode.get(key);
		if (node == null)
			return "";
		
		return node.asText();
	}
	
}