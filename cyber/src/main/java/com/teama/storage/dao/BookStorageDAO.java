package com.teama.storage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;
import com.teama.storage.dto.BookStorageDTO;
import com.teama.storage.dto.BookStorageViewDTO;

@Repository
public class BookStorageDAO extends AbstractDAO {

	public List<BookStorageViewDTO> getBookList() {
		return sqlSession.selectList("bookStorage.getBookList");
	}
	
	public BookStorageViewDTO getBookByBookNo(int bookNo) {
		return sqlSession.selectOne("bookStorage.getBookByBookNo", bookNo);
	}
	
	public int increaseLoanCountByBookNo(int bookNo) {
		return sqlSession.update("bookStorage.increaseLoanCountByBookNo", bookNo);
	}
	
	public List<BookStorageViewDTO> getUnregisteredBookList() {
		return sqlSession.selectList("bookStorage.getUnregisteredBookList");
	}

	public int insertBook(BookStorageDTO dto) {
		return sqlSession.insert("bookStorage.insertBook", dto);
	}

	public int updateMaxCount(int bookNo, int maxCount) {
		Map<String, Object> map = Map.of(
				"bookNo", bookNo,
				"maxCount", maxCount);
		
		return update("bookStorage.updateMaxCount", map);
	}
}
