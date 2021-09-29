package com.teama.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teama.admin.dao.AdminDAO;
import com.teama.ebook.dao.EbookDAO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;
	@Autowired
	private EbookDAO ebookDAO;
	
	
	@Override
	public List<Map<String, Object>> bookList() {
		return adminDAO.bookList();
	}

	@Override
	public List<Map<String, Object>> memberList() {
		return adminDAO.memberList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> searchBook(Map<String, Object> infoMap) {
		String searchType = (String)infoMap.get("searchType");
		String searchValue = (String)infoMap.get("searchValue");
		if (searchValue.isEmpty())
			return null;
		
		Map<String, Object> map = null;
		
		try {
			HashMap<String, Object> searchInfo = new HashMap<String, Object>();
			searchInfo.put("pageNo", 1);
			
			SearchType type = SearchType.valueOf(searchType);
			if (type == SearchType.Title)
				searchInfo.put("title", searchValue);
			else if (type == SearchType.ISBN)
				searchInfo.put("isbn", searchValue);
				
			JSONObject searchList = ebookDAO.ebookSearch(searchInfo);

			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(searchList.toString(), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

	@Override
	public List<Map<String, Object>> storedBookList(Map<String, Object> infoMap) {
		return adminDAO.storedBookList(infoMap);
	}
	
	@Override
	public void registBook(Map<String, Object> infoMap) {
		adminDAO.insertBook(infoMap);
	}

	@Override
	public List<Map<String, Object>> notStoredBookList() {
		return adminDAO.notStoredBookList();
	}

	@Override
	public int updateStoredBook(Map<String, Object> infoMap) {
		int result = 0;
		
		Map<String, Object> foundMap = adminDAO.getStoredBook(infoMap);
		if (foundMap == null)
			result = adminDAO.insertStorageBook(infoMap);
		else
			result = adminDAO.updateStoredBook(infoMap);

		return result;
	}

	@Override
	public List<Map<String, Object>> bookLoanListByMemberNo(Map<String, Object> infoMap) {
		return adminDAO.bookLoanListByMemberNo(infoMap);
	}
}
