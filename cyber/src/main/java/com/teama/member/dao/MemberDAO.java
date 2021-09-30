package com.teama.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;

@Repository
public class MemberDAO extends AbstractDAO {
	@Autowired
	private static SqlSession sqlSession;

	public Map<String, Object> login(Map<String, Object> map) {

		return selectOne("login", map);
	}

	public static int join(Map<String, Object> map) {

		return sqlSession.insert("member.join", map);
	}
}