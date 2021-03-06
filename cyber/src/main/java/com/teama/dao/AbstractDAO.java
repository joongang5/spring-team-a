package com.teama.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDAO {

	protected Log log = LogFactory.getLog(AbstractDAO.class);

	@Autowired
	protected SqlSessionTemplate sqlSession;

	protected void printQueryId(String queryId) {
		if (log.isDebugEnabled()) {
			log.debug("\t QueryId \t: " + queryId);
		}
	}

	public List<Map<String, Object>> selectList(String queryId) {
		printQueryId(queryId);
		return sqlSession.selectList(queryId);
	}

	public List<Map<String, Object>> selectList(String queryId, int value) {
		return sqlSession.selectList(queryId, value);
	}

	public List<Map<String, Object>> selectList(String queryId, Map<String, Object> map) {
		return sqlSession.selectList(queryId, map);
	}

	public Map<String, Object> selectOne(String queryId) {
		return sqlSession.selectOne(queryId);
	}

	public int getCountByKey(String queryId, int key) {
		return sqlSession.selectOne(queryId, key);
	}

	public Map<String, Object> selectOne(String queryID, Map<String, Object> map) {
		printQueryId(queryID);
		return sqlSession.selectOne(queryID, map);
	}

	public int insert(String queryId, Map<String, Object> map) {
		return sqlSession.insert(queryId, map);
	}

	public int update(String queryId, Map<String, Object> map) {
		return sqlSession.update(queryId, map);
	}

	public int delete(String queryId, Map<String, Object> map) {
		return sqlSession.delete(queryId, map);
	}
}
