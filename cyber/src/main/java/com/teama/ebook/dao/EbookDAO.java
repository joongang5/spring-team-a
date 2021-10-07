package com.teama.ebook.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;
import com.teama.ebook.dto.EbookDTO;

@Repository
public class EbookDAO extends AbstractDAO{
	public int ebookAdd(Map<String, Object> map) {		
		return insert("ebook.ebookAdd", map);
	}
	
	public List<EbookDTO> getEbookList() {
		return sqlSession.selectList("ebook.getEbookList");
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
}
