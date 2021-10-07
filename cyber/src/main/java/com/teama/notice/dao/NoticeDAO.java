package com.teama.notice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;

@Repository("noticeDAO")
public class NoticeDAO extends AbstractDAO {
	
	//게시글 불러오기
	public List<Map<String, Object>> noticeList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("notice.noticeList", map);
	}
	
	//페이징 totalCount
	public int totalCount(Map<String, Object> map) {
		return Integer.parseInt( String.valueOf (selectOne("notice.totalCount", map).get("totalCount")) );
	}
	
	//게시글 상세보기
	public Map<String, Object> detail(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("notice.detail", map);
	}
	
	//게시글 글쓰기
	public int write(Map<String, Object> map) {
		return insert("notice.write", map);
	}
	
	//게시글 삭제
	public int delete(Map<String, Object> map) {
		return delete("notice.delete", map);
	}
	
	//게시글 수정
	
	//게시글 조회수
	public int count(Map<String, Object> map) {
		return update("notice.count", map);
	}

}
