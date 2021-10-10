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

	public List<Map<String, Object>> getBookMapList() {
		return sqlSession.selectList("bookStorage.getBookMapList");
	}
	
	public List<Map<String, Object>> getBookMapList(int firstIndex, int recordCountPerPage) {
		Map<String, Object> map = Map.of(
				"firstIndex", firstIndex,
				"recordCountPerPage", recordCountPerPage);
		return sqlSession.selectList("bookStorage.getBookMapList", map);
	}
	
	public int getTotalCount() {
		return sqlSession.selectOne("bookStorage.getTotalCount");
	}
	
	public BookStorageViewDTO getBookByBookNo(int bookNo) {
		return sqlSession.selectOne("bookStorage.getBookByBookNo", bookNo);
	}

	public Map<String, Object> getBookMapByBookNo(int bookNo) {
		return sqlSession.selectOne("bookStorage.getBookMapByBookNo", bookNo);
	}
	
	public int increaseLoanCountByBookNo(int bookNo) {
		return sqlSession.update("bookStorage.increaseLoanCountByBookNo", bookNo);
	}
	
	public List<BookStorageViewDTO> getUnregisteredBookList() {
		return sqlSession.selectList("bookStorage.getUnregisteredBookList");
	}

	public List<Map<String, Object>> getUnregisteredBookMapList() {
		return sqlSession.selectList("bookStorage.getUnregisteredBookMapList");
	}

	public List<Map<String, Object>> getUnregisteredBookMapList(int firstIndex, int recordCountPerPage) {
		Map<String, Object> map = Map.of(
				"firstIndex", firstIndex,
				"recordCountPerPage", recordCountPerPage);
		return sqlSession.selectList("bookStorage.getUnregisteredBookMapList", map);
	}

	public int getTotalUnregisteredCount() {
		return sqlSession.selectOne("bookStorage.getTotalUnregisteredCount");
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
