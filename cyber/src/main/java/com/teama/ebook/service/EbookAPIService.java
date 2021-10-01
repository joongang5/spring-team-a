package com.teama.ebook.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public interface EbookAPIService {
	//도서 검색
	public JSONObject ebookSearch(Map<String, Object> map);
	
	public List<Map<String, Object>> ebookSearchKakao(String isbn);

	public Map<String, Object> ebookCrawler(String url);
	
	public int ebookAdd(Map<String, Object> map);
}
