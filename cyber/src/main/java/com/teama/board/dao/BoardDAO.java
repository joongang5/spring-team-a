package com.teama.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends AbstractDAO {
	
	//게시글 불러오기
	public List<Map<String, Object>> boardList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("board.boardList", map);
	}
	
	//페이징 totalCount
	public int totalCount(Map<String, Object> map) {
		return Integer.parseInt( String.valueOf (selectOne("board.totalCount", map).get("totalCount")) );
	}
	
	//게시글 상세보기
	public Map<String, Object> detail(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("board.detail", map);
	}
	
	//게시글 글쓰기
	public int write(Map<String, Object> map) {
		return insert("board.write", map);
	}
	
	//게시글 삭제
	public int delete(Map<String, Object> map) {
		return delete("board.delete", map);
	}
	
	//게시글 수정
	public int update(Map<String, Object> map) {
		return update("board.update", map);
	}
	
	//게시글 조회수
	public int count(Map<String, Object> map) {
		return update("board.count", map);
	}

}