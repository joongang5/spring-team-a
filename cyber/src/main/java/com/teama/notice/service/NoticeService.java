package com.teama.notice.service;

import java.util.List;
import java.util.Map;

public interface NoticeService {

	//게시글 불러오기
	List<Map<String, Object>> noticeList(Map<String, Object> map);
	
	//페이징 totalCount
	public int totalCount(Map<String, Object> map);
	
	//게시글 상세보기
	public Map<String, Object> detail(Map<String, Object> map);
	
	//게시글 글쓰기
	
	//게시글 삭제
	public int delete(Map<String, Object> map);
	
	//게시글 수정
	
	//게시글 조회수
	public int count(Map<String, Object> map);
}
