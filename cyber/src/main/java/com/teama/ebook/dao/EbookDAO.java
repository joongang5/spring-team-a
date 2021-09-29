package com.teama.ebook.dao;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import com.teama.api.util.HttpURLConnUtil;

@Repository
public class EbookDAO {

	@SuppressWarnings("unchecked")
	public JSONObject ebookSearch(Map<String, Object> map) throws Exception {
		String serviceKey = "e1105e7ff1c474e3dd03dffcdf49ade50be0ba92155bd191bdbf3be98af7d545";
		String url = "http://seoji.nl.go.kr/landingPage/SearchApi.do";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cert_key", serviceKey);
		params.put("result_style", "json");
		params.put("page_size", "10");
		params.put("page_no", String.valueOf(map.get("pageNo")));
		
		if (map.get("searchValue") != null) {
			params.put((String)map.get("searchTarget"), (String) map.get("searchValue"));
		}
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(HttpURLConnUtil.doGetRequest(url, null, params));
		List<Map<String, Object>> docsMap = (List<Map<String, Object>>) jsonObject.get("docs");
		List<Map<String, Object>> kakaoList = null;
		for (int i = 0; i < docsMap.size(); i++) {
			//System.out.println(docsMap.get(i).get("EA_ISBN").equals(""));
			if (!docsMap.get(i).get("EA_ISBN").equals("")) {
				if (docsMap.get(i).get("TITLE").equals("") || docsMap.get(i).get("TITLE_URL").equals("")) {
					{
						kakaoList = ebookSearchKakao((String) docsMap.get(i).get("EA_ISBN"));
						//System.out.println("kakaoList = " + kakaoList);
						if (kakaoList != null && kakaoList.size() != 0) {
							//System.out.println("카카오 추가 리스트 " + kakaoList);
							if (kakaoList.get(0).get("title") != null) {
								//System.out.println("기존 타이틀 " + docsMap.get(i).get("TITLE"));
								//System.out.println("변경 타이틀 " + kakaoList.get(0).get("title"));
								docsMap.get(i).put("TITLE", kakaoList.get(0).get("title"));
							}
							if (kakaoList.get(0).get("thumbnail") != null) {
								//System.out.println("기존 사진 " + docsMap.get(i).get("TITLE_URL"));
								//System.out.println("변경 사진 " + kakaoList.get(0).get("thumbnail"));
								docsMap.get(i).put("TITLE_URL", kakaoList.get(0).get("thumbnail"));
							}
						}
					}
				}
			} else {
				//EA ISBN 미존재 시 SET_ISBN 대체
				docsMap.get(i).put("EA_ISBN", docsMap.get(i).get("SET_ISBN"));
			}	//TITLE_URL 미존재 시 기본 이미지 대체
			if (docsMap.get(i).get("TITLE_URL").equals("")) {
				docsMap.get(i).put("TITLE_URL", "./resources/img/thumbnail.gif");
				//System.out.println("docsMAp " + i + " : " + docsMap.get(i).get("TITLE_URL"));
			}
		}
		jsonObject.put("docs", docsMap);
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> ebookSearchKakao(String isbn) throws Exception {
		String serviceKey = "KakaoAK b6e603937314371fe828d320341a4c07";
		String url = "https://dapi.kakao.com/v3/search/book";
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> properties = new HashMap<String, String>();
		params.put("sort", URLEncoder.encode("accuracy", "UTF-8"));
		params.put("query", URLEncoder.encode(isbn, "UTF-8"));
		params.put("target", URLEncoder.encode("isbn", "UTF-8"));
		properties.put("Authorization", serviceKey);
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(HttpURLConnUtil.doGetRequest(url, properties, params));
		//List<Map<String, Object>> kakao = (List<Map<String, Object>>) jsonObject.get("documents");
		//System.out.println("카카오 documents" + kakao);
		// kakao.get(0).get("title");
		return (List<Map<String, Object>>) jsonObject.get("documents");
	}

}
