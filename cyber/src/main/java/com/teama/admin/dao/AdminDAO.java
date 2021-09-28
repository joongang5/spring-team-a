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
}
