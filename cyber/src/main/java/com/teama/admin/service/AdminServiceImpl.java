package com.teama.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teama.admin.dao.AdminDAO;
import com.teama.ebook.service.EbookAPIServiceImpl;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;
	@Autowired
	private EbookAPIServiceImpl ebookAPIService;
	
	
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
				
			JSONObject searchList = ebookAPIService.ebookSearch(searchInfo);

			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(searchList.toString(), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

	@Override
	public Map<String, Object> getStoredBook(Map<String, Object> infoMap) {
		return adminDAO.getStoredBook(infoMap);
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

	@Override
	public Map<String, Object> loanBook(Map<String, Object> infoMap) {
		int result = adminDAO.loanBookFromStorage(infoMap);
		if (result <= 0)
			return null;
		
		result = adminDAO.loanBookToMember(infoMap);
		
		return null;
	}
	
	@Override
	public List<Map<String, Object>> searchMember(Map<String, Object> infoMap) {
		String searchType = String.valueOf(infoMap.get("search_type"));
		String searchValue = String.valueOf(infoMap.get("keyword"));
		infoMap.put(searchType, searchValue);
		
		List<Map<String, Object>> result = null;
		if (searchType.equals("id")) {
			result = adminDAO.memberListById(infoMap);
		} else if (searchType.equals("name")) {
			result = adminDAO.memberListByName(infoMap);
		}
		
		return result;
	}
}
