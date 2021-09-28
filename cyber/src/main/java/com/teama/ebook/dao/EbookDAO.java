package com.teama.ebook.dao;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;


@Repository
public class EbookDAO {

	public JSONObject ebookSearch(Map<String, Object> map) throws Exception {
		String serviceKey = "e1105e7ff1c474e3dd03dffcdf49ade50be0ba92155bd191bdbf3be98af7d545";
		StringBuilder sb = new StringBuilder(
				"http://seoji.nl.go.kr/landingPage/SearchApi.do");
		
		sb.append("?" + URLEncoder.encode("cert_key", "UTF-8") + "=" + serviceKey);
		sb.append("&" + URLEncoder.encode("result_style", "UTF-8") + "=json");
		sb.append("&" + URLEncoder.encode("page_no", "UTF-8") + "=1");
		sb.append("&" + URLEncoder.encode("page_size", "UTF-8") + "=10");
		if(map.get("title") != null) {
			sb.append("&" + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode((String) map.get("title"),"UTF-8"));			
		}
		
		URL url = new URL(sb.toString());
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(url.openStream()));
		System.out.println("Json");
		System.out.println(jsonObject.toJSONString());
		System.out.println("Json");

		return jsonObject;
	}

}
