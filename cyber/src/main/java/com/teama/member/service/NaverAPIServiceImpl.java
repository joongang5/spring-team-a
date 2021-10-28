package com.teama.member.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teama.api.util.HttpURLConnUtil;

@Service("naverAPIService")
public class NaverAPIServiceImpl{

	private final String CLIENT_ID = "wn_m3HL4uolGFA5Fudbr";
	private final String CLIENT_SECRET = "VPT8CIEF4K";
	private final String BASE_URL_AUTH = "https://nid.naver.com/oauth2.0/authorize";
	private final String BASE_URL_TOKEN = "https://nid.naver.com/oauth2.0/token";
	private final String BASE_URL_PROFILE = "https://openapi.naver.com/v1/nid/me";
	private final String REDIRECT_URI_AUTH = "http://localhost:8080/cyber/member/onNaverLoginCallback.do";
//	private final String REDIRECT_URI_AUTH = "http://15.164.94.26:8080/cyber/member/onNaverLoginCallback.do";
	
		
	public String requestAuth(HttpServletRequest request) throws UnsupportedEncodingException {
		String redirectUri = URLEncoder.encode(REDIRECT_URI_AUTH, "UTF-8");
		String state = new BigInteger(130, new SecureRandom()).toString();
		request.getSession().setAttribute("state", state);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("response_type", "code");
		params.put("client_id", CLIENT_ID);
		params.put("redirect_uri", redirectUri);
		params.put("state", state);

		String strParams = HttpURLConnUtil.parseParams(params);
		return BASE_URL_AUTH + "?" + strParams;
	}

	@SuppressWarnings("unchecked")
	public String requestToken(String code, String state) throws JsonMappingException, JsonProcessingException {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("client_id", CLIENT_ID);
		params.put("client_secret", CLIENT_SECRET);
		params.put("grant_type", "authorization_code");
		params.put("state", state);
		params.put("code", code);

		ObjectMapper tokenMapper = new ObjectMapper();
		String tokenResponse = HttpURLConnUtil.doGetRequest(BASE_URL_TOKEN, null, params);
		Map<String, String> tokenMap = tokenMapper.readValue(tokenResponse, Map.class);
		String accessToken = tokenMap.get("access_token");
//		String refreshToken = tokenMap.get("refresh_token");
//		String tokenType = tokenMap.get("token_type");
//		String expiresIn = tokenMap.get("expires_in");

		return accessToken;
	}
	
	@SuppressWarnings("unused")
	private String getStrValueFromJsonNode(JsonNode parentNode, String key) {
		JsonNode node = parentNode.get(key);
		if (node == null)
			return "";
		
		return node.asText();
	}

	public Map<String, Object> requestProfile(String accessToken) throws JsonMappingException, JsonProcessingException {

		String header = "Bearer " + accessToken;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", header);

		String profileResponse = HttpURLConnUtil.doGetRequest(BASE_URL_PROFILE, headers, null);

		ObjectMapper profileMapper = new ObjectMapper();
		JsonNode rootNode = profileMapper.readTree(profileResponse);
		JsonNode responseNode = rootNode.get("response");
		String id = getStrValueFromJsonNode(responseNode, "id");
		String email = getStrValueFromJsonNode(responseNode, "email");
		String name = getStrValueFromJsonNode(responseNode, "name");
		String nickname = getStrValueFromJsonNode(responseNode, "nickname");
		String birthday = getStrValueFromJsonNode(responseNode, "birthday");
		
		Map<String, Object> profileMap = new HashMap<String, Object>();
		profileMap.put("id", id);
		profileMap.put("pw", "");
		profileMap.put("email", email);
		profileMap.put("name", name);
		profileMap.put("platform", 1);
		
		return profileMap;
	}
}