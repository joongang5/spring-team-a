package com.teama.ebook.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;
import com.teama.ebook.dto.EbookDTO;
import com.teama.ebook.dto.EbookReviewDTO;

@Repository
public class EbookDAO extends AbstractDAO {
	public int ebookAdd(Map<String, Object> map) {
		return insert("ebook.ebookAdd", map);
	}

	public List<EbookDTO> getEbookList(Map<String, Object> map) {
		return sqlSession.selectList("ebook.getEbookList", map);
	}

	public EbookDTO getEbookListByTitle(String title) {
		return sqlSession.selectOne("ebook.getEbookListByTitle", title);
	}

	public EbookDTO getEbookByISBN(String isbn) {
		return sqlSession.selectOne("ebook.getEbookByISBN", isbn);
	}

	public EbookDTO getEbook(int bookNo) {
		return sqlSession.selectOne("ebook.getEbookByNo", bookNo);
	}

	public EbookDTO ebookDetail(String isbn) {
		return sqlSession.selectOne("ebook.getEbookDetail", isbn);
	}

	public int ebookInsertReview(EbookReviewDTO dto) {
		return sqlSession.insert("ebook.insertReview", dto);
	}

	public List<EbookReviewDTO> ebookReviewList(int bookno) {
		return sqlSession.selectList("ebook.reviewList", bookno);
	}

	public int insertBook(EbookDTO map) {
		return sqlSession.insert("ebook.insertBook", map);
	}

	public List<EbookDTO> getNewList() {
		return sqlSession.selectList("ebook.getNewList");
	}
}
