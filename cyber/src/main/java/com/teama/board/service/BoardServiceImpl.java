package com.teama.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.board.dao.BoardDAO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	//게시글 불러오기
	public List<Map<String, Object>> boardList(Map<String, Object> map) {
		return boardDAO.boardList(map);
	}
	
	//페이징 totalCount
	public int totalCount(Map<String, Object> map) {
		return boardDAO.totalCount(map);
	}
	
	//게시글 상세보기
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return boardDAO.detail(map);
	}
	
	//이전글 다음글
	@Override
	public Map<String, Object> preNextPage(Map<String, Object> map) {
		return boardDAO.preNextPage(map);
	}
	
	//게시글 글쓰기
	@Override
	public int write(Map<String, Object> map) {
		return boardDAO.write(map);
	}
	
	//게시글 삭제
	@Override
	public int delete(Map<String, Object> map) {
		return boardDAO.delete(map);
	}
	
	//게시글 수정
	@Override
	public int update(Map<String, Object> map) {
		return boardDAO.update(map);
	}
	
	//게시글 조회수
	@Override
	public int count(Map<String, Object> map) {
		return boardDAO.count(map);
	}
	
	//게시물 댓글 불러오기
	@Override
	public List<Map<String, Object>> boardCommentList(Map<String, Object> map) {
		return boardDAO.boardCommentList(map);
	}
	
	//게시물 댓글쓰기
	@Override
	public int commentWrite(Map<String, Object> map) {
		return boardDAO.commentWrite(map);
	}
	
	//게시물 댓글삭제
	@Override
	public int commentDelete(Map<String, Object> map) {
		return boardDAO.commentDelete(map);
	}
	
	//게시물 댓글수정
	@Override
	public int commentUpdate(Map<String, Object> map) {
		return boardDAO.commentUpdate(map);
	}
	
}
