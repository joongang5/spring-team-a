package com.teama.ebook.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.ebook.dao.EbookDAO;
import com.teama.ebook.dto.EbookDTO;
import com.teama.ebook.dto.EbookReviewDTO;

@Service("ebookService")
public class EbookServiceImpl implements EbookService {

	@Autowired
	private EbookDAO ebookDAO;
	
	@Override
	public List<EbookDTO> ebookSearch(Map<String, Object> map) throws Exception {
		return ebookDAO.getEbookSearch(map);
	}
	
	@Override
	public int ebookAdd(Map<String, Object> map) throws Exception {
		return ebookDAO.ebookAdd(map);
	}
	
	@Override
	public List<EbookDTO> getEbookList(Map<String, Object> map) {
		return ebookDAO.getEbookList(map);
	}

	@Override
	public int insertBook(EbookDTO map) {
		return ebookDAO.insertEbook("ebook.insertBook", map);
	}
	
	@Override
	public EbookDTO getEbook(int bookNo) {
		return ebookDAO.getEbook(bookNo);
	}
	@Override
	public EbookDTO ebookDetail(String isbn) {
		return ebookDAO.ebookDetail(isbn);
	}
	@Override
	public int ebookInsertReview(EbookReviewDTO dto) {
		return ebookDAO.ebookInsertReview(dto);
	}
	@Override
	public List<EbookReviewDTO> ebookReviewList(int bookno) {
		return ebookDAO.ebookReviewList(bookno);
	}


}
