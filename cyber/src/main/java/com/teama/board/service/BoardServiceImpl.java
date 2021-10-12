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
	
}
