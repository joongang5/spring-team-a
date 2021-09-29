package com.teama.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;

@Repository
public class AdminDAO extends AbstractDAO {

	public List<Map<String, Object>> bookList() {
		return selectList("admin.bookList");
	}

	public List<Map<String, Object>> memberList() {
		return selectList("admin.memberList");
	}

	public int insertBook(Map<String, Object> infoMap) {
		return insertMap("admin.insertBook", infoMap);
	}

	public Map<String, Object> getStoredBook(Map<String, Object> infoMap) {
		return selectOne("admin.getStoredBook");
	}
	
	public List<Map<String, Object>> storedBookList(Map<String, Object> infoMap) {
		return selectList("admin.getStoredBook", infoMap);
	}

	public List<Map<String, Object>> notStoredBookList() {
		return selectList("admin.notStoredBookList");
	}

	public int insertStorageBook(Map<String, Object> infoMap) {
		return insertMap("admin.insertStorageBook", infoMap);
	}

	public int updateStoredBook(Map<String, Object> infoMap) {
		return updateMap("admin.updateStoredBook", infoMap);
	}
}
