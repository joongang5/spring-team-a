package com.teama.ebook.service;

import java.util.Map;

import org.json.simple.JSONObject;

public interface ListEbookService {
	//도서 검색
	public JSONObject ebookSearch(Map<String, Object> map) throws Exception;
	
	public int ebookAdd(Map<String, Object> map) throws Exception;
 	
}
