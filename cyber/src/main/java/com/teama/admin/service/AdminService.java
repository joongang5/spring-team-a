package com.teama.admin.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

	public List<Map<String, Object>> bookList();
	public List<Map<String, Object>> memberList();
	
	public Map<String, Object> searchBook(SearchType searchType, String searchValue);
	public void registBook(Map<String, Object> infoMap);
}
