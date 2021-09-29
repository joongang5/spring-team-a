package com.teama.notice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;

@Repository
public class NoticeDAO extends AbstractDAO {

	public List<Map<String, Object>> selectList() {
		return (List<Map<String, Object>>) selectList("notice.list");
	}

	public Map<String, Object> detail(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("notice.detail", map);
	}
}
