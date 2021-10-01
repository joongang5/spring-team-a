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

	public List<Map<String, Object>> memberListById(Map<String, Object> infoMap) {
		return selectList("admin.memberListById", infoMap);
	}

	public List<Map<String, Object>> memberListByName(Map<String, Object> infoMap) {
		return selectList("admin.memberListByName", infoMap);
	}
	
	public List<Map<String, Object>> memberListByEmail(Map<String, Object> infoMap) {
		return selectList("admin.memberListByEmail", infoMap);
	}
	
	public List<Map<String, Object>> memberListByJoinDate(Map<String, Object> infoMap) {
		return selectList("admin.memberListByJoinDate", infoMap);
	}
	
	public int insertBook(Map<String, Object> infoMap) {
		return insert("admin.insertBook", infoMap);
	}

	public Map<String, Object> getStoredBook(Map<String, Object> infoMap) {
		return selectOne("admin.getStoredBook", infoMap);
	}
	
	public List<Map<String, Object>> storedBookList(Map<String, Object> infoMap) {
		return selectList("admin.getStoredBook", infoMap);
	}

	public List<Map<String, Object>> notStoredBookList() {
		return selectList("admin.notStoredBookList");
	}

	public int insertStorageBook(Map<String, Object> infoMap) {
		return insert("admin.insertStorageBook", infoMap);
	}

	public int updateStoredBook(Map<String, Object> infoMap) {
		return update("admin.updateStoredBook", infoMap);
	}
	
	public List<Map<String, Object>> bookLoanListByMemberNo(Map<String, Object> infoMap) {
		return selectList("admin.bookLoanListByMemberNo", infoMap);
	}
	
	public int loanBookFromStorage(Map<String, Object> infoMap) {
		return update("admin.loanBookFromStorage", infoMap);
	}
	
	public int loanBookToMember(Map<String, Object> infoMap) {
		return insert("admin.loanBookToMember", infoMap);
	}
}
