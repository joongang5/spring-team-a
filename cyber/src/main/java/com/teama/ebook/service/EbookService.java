package com.teama.ebook.service;

import java.util.List;
import java.util.Map;

import com.teama.ebook.dto.EbookDTO;
import com.teama.ebook.dto.EbookReviewDTO;

public interface EbookService {
	// 도서 검색
	public int ebookAdd(Map<String, Object> map) throws Exception;

	public List<EbookDTO> getEbookList(Map<String, Object> map);

	public int insertBook(EbookDTO ebookDTO);

	public EbookDTO getEbook(int bookNo);

	public EbookDTO ebookDetail(String isbn);

	public int ebookInsertReview(EbookReviewDTO dto);

	public List<EbookReviewDTO> ebookReviewList(int bookno);

	public List<EbookDTO> getNewList();
}