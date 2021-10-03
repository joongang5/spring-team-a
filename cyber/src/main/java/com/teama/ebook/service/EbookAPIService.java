package com.teama.ebook.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public interface EbookAPIService {
	//도서 검색
	public JSONObject ebookSearch(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ebookSearchKakao(String isbn) throws Exception;

	public Map<String, Object> ebookCrawler(String url) throws Exception;
	
	public int ebookAdd(Map<String, Object> map);
	
	public Map<String, Object> searchEbook(String searchType, String searchValue, int pageNo);
}
