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
	
	//이전글 다음글
	public Map<String, Object> preNextPage(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("board.preNextPage", map);
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
	
	//게시물 댓글 불러오기
	public List<Map<String, Object>> boardCommentList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("board.boardCommentList", map);
	}
	
	//게시물 댓글쓰기
	public int commentWrite(Map<String, Object> map) {
		return insert("board.commentWrite", map);
	}
	
	//게시물 댓글삭제
	public int commentDelete(Map<String, Object> map) {
		return delete("board.commentDelete", map);
	}
	
	//게시물 댓글수정
	public int commentUpdate(Map<String, Object> map) {
		return update("board.commentUpdate", map);
	}

}
