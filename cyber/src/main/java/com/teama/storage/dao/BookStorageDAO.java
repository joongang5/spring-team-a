package com.teama.storage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;
import com.teama.storage.dto.BookStorageDTO;
import com.teama.storage.dto.BookStorageViewDTO;

@Repository
public class BookStorageDAO extends AbstractDAO {
	
	public List<BookStorageDTO> getBookList() {
		return sqlSession.selectList("bookStorage.getBookList");
	}
	
	public List<BookStorageDTO> getNeedAutoLoanList() {
		return sqlSession.selectList("bookStorage.getNeedAutoLoanList");
	}
	
	public List<BookStorageViewDTO> getViewList() {
		return sqlSession.selectList("bookStorage.getViewList");
	}

	public List<BookStorageViewDTO> getViewList(String title, int firstIndex, int recordCountPerPage) {
		Map<String, Object> map = Map.of(
				"title", title,
				"firstIndex", firstIndex,
				"recordCountPerPage", recordCountPerPage);
		
		return sqlSession.selectList("bookStorage.getViewListByTitle", map);
	}
	
	public List<Map<String, Object>> getViewMapList() {
		return sqlSession.selectList("bookStorage.getViewMapList");
	}
	
	public List<Map<String, Object>> getViewMapList(int firstIndex, int recordCountPerPage) {
		Map<String, Object> map = Map.of(
				"firstIndex", firstIndex,
				"recordCountPerPage", recordCountPerPage);
		return sqlSession.selectList("bookStorage.getViewMapList", map);
	}
	
	public int getTotalCount() {
		return sqlSession.selectOne("bookStorage.getTotalCount");
	}

	public int getTotalCount(String title) {
		return sqlSession.selectOne("bookStorage.getTotalCountByTitle", title);
	}
	
	public BookStorageViewDTO getViewByBookNo(int bookNo) {
		return sqlSession.selectOne("bookStorage.getViewByBookNo", bookNo);
	}

	public Map<String, Object> getViewMapByBookNo(int bookNo) {
		return sqlSession.selectOne("bookStorage.getViewMapByBookNo", bookNo);
	}
	
	public int increaseLoanCountByBookNo(int bookNo) {
		return sqlSession.update("bookStorage.increaseLoanCountByBookNo", bookNo);
	}

	public int increaseReserveCountByBookNo(int bookNo) {
		return sqlSession.update("bookStorage.increaseReserveCountByBookNo", bookNo);
	}

	public int decreaseLoanCountByBookNo(int bookNo) {
		return sqlSession.update("bookStorage.decreaseLoanCountByBookNo", bookNo);
	}

	public int decreaseReserveCountByBookNo(int bookNo) {
		return sqlSession.update("bookStorage.decreaseReserveCountByBookNo", bookNo);
	}

	public int reserveToLoanByBookNo(int bookNo) {
		return sqlSession.update("bookStorage.reserveToLoanByBookNo", bookNo);
	}

	public List<BookStorageViewDTO> getUnregisteredViewList() {
		return sqlSession.selectList("bookStorage.getUnregisteredBookList");
	}

	public List<Map<String, Object>> getUnregisteredViewMapList() {
		return sqlSession.selectList("bookStorage.getUnregisteredViewMapList");
	}

	public List<Map<String, Object>> getUnregisteredViewMapList(int firstIndex, int recordCountPerPage) {
		Map<String, Object> map = Map.of(
				"firstIndex", firstIndex,
				"recordCountPerPage", recordCountPerPage);
		return sqlSession.selectList("bookStorage.getUnregisteredViewMapList", map);
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
	
	public List<BookStorageViewDTO> getPopularViewList() {
		return sqlSession.selectList("bookStorage.getPopularViewList");
	}
}
