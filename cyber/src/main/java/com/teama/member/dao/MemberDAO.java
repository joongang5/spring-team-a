package com.teama.member.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;

@Repository
public class MemberDAO extends AbstractDAO {

	public Map<String, Object> login(Map<String, Object> map) {
		return selectOne("login", map);
	}

	public int join(Map<String, Object> map) {
		return insert("join", map);
	}

	public boolean isUsableId(Map<String, Object> map) {
		int id = Integer.parseInt(String.valueOf(map.get("id")));
		
		int result = getCountByKey("idCheck", id);
		
		return result <= 0;
	}
}