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
	public Map<String, Object> searchBook(SearchType searchType, String searchValue) {
		if (searchValue.isEmpty())
			return null;
		
		Map<String, Object> map = null;
		
		try {
			HashMap<String, Object> searchInfo = new HashMap<String, Object>();
			searchInfo.put("pageNo", 1);
			
			if (searchType == SearchType.Title)
				searchInfo.put("title", searchValue);
			else if (searchType == SearchType.ISBN)
				searchInfo.put("isbn", searchValue);
				
			JSONObject searchList = ebookDAO.ebookSearch(searchInfo);

			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(searchList.toString(), Map.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}

	@Override
	public void registBook(Map<String, Object> infoMap) {
		adminDAO.insertBook(infoMap);
	}
}
