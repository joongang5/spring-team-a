package com.teama.admin.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

	public List<Map<String, Object>> bookList();
	public List<Map<String, Object>> memberList();
	
	public Map<String, Object> searchBook(Map<String, Object> infoMap);
	public void registBook(Map<String, Object> infoMap);
	
	public Map<String, Object> getStoredBook(Map<String, Object> infoMap);
	public List<Map<String, Object>> storedBookList(Map<String, Object> infoMap);
	public List<Map<String, Object>> notStoredBookList();
	public int updateStoredBook(Map<String, Object> infoMap);
	
	public List<Map<String, Object>> bookLoanListByMemberNo(Map<String, Object> infoMap);
	public Map<String, Object> loanBook(Map<String, Object> infoMap);
	public List<Map<String, Object>> searchMember(Map<String, Object> infoMap);
}
