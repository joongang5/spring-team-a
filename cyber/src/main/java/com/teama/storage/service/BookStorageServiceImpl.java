package com.teama.storage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.storage.dao.BookStorageDAO;
import com.teama.storage.dto.BookStorageDTO;
import com.teama.storage.dto.BookStorageViewDTO;

@Service("bookStorageService")
public class BookStorageServiceImpl implements BookStorageService {

	@Autowired
	private BookStorageDAO bookStorageDAO;

	@Override
	public BookStorageDTO getBook(int bookNo) {
		return bookStorageDAO.getBook(bookNo);
	}
	
	public Map<String, Object> getBookMap(int bookNo) {
		return bookStorageDAO.getBookMap(bookNo);
	}
	
	@Override
	public BookStorageViewDTO getView(int bookNo) {
		return bookStorageDAO.getViewByBookNo(bookNo);
	}

	@Override
	public Map<String, Object> getViewMap(int bookNo) {
		return bookStorageDAO.getViewMapByBookNo(bookNo);
	}

	@Override
	public List<BookStorageDTO> getBookList() {
		return bookStorageDAO.getBookList();
	}

	@Override
	public List<BookStorageDTO> getNeedAutoLoanList() {
		return bookStorageDAO.getNeedAutoLoanList();
	}

	@Override
	public List<BookStorageViewDTO> getViewList() {
		return bookStorageDAO.getViewList();
	}

	@Override
	public List<BookStorageViewDTO> getViewList(String title, int firstIndex, int recordCountPerPage) {
		return bookStorageDAO.getViewList(title, firstIndex, recordCountPerPage);
	}
	
	@Override
	public List<Map<String, Object>> getViewMapList() {
		return bookStorageDAO.getViewMapList();
	}

	@Override
	public List<Map<String, Object>> getViewMapList(int firstIndex, int recordCountPerPage) {
		return bookStorageDAO.getViewMapList(firstIndex, recordCountPerPage);
	}

	@Override
	public List<BookStorageViewDTO> getUnregisteredViewList() {
		return bookStorageDAO.getUnregisteredViewList();
	}

	@Override
	public List<Map<String, Object>> getUnregisteredViewMapList() {
		return bookStorageDAO.getUnregisteredViewMapList();
	}

	@Override
	public List<Map<String, Object>> getUnregisteredViewMapList(int firstIndex, int recordCountPerPage) {
		return bookStorageDAO.getUnregisteredViewMapList(firstIndex, recordCountPerPage);
	}

	@Override
	public int getTotalCount() {
		return bookStorageDAO.getTotalCount();
	}

	@Override
	public int getTotalCount(String title) {
		return bookStorageDAO.getTotalCount(title);
	}
	
	@Override
	public int getTotalUnregisteredCount() {
		return bookStorageDAO.getTotalUnregisteredCount();
	}

	@Override
	public int insertBook(BookStorageDTO dto) {
		return bookStorageDAO.insertBook(dto);
	}

	@Override
	public int insertBookList(List<Map<String, Object>> dtoList) {
		return bookStorageDAO.insertBookList(dtoList);
	}
	
	@Override
	public int updateMaxCount(BookStorageDTO dto) {
		int result = 0;

		BookStorageDTO resultDTO = getView(dto.getBook_no());
		if (resultDTO == null)
			result = insertBook(dto);
		else
			result = updateMaxCount(dto.getBook_no(), dto.getMax_count());

		return result;
	}

	@Override
	public int updateMaxCount(int bookNo, int maxCount) {
		return bookStorageDAO.updateMaxCount(bookNo, maxCount);
	}

	@Override
	public int increaseLoanCount(int bookNo) {
		return bookStorageDAO.increaseLoanCountByBookNo(bookNo);
	}

	@Override
	public int increaseReserveCount(int bookNo) {
		return bookStorageDAO.increaseReserveCountByBookNo(bookNo);
	}

	@Override
	public int decreaseLoanCount(int bookNo) {
		return bookStorageDAO.decreaseLoanCountByBookNo(bookNo);
	}

	@Override
	public int decreaseReserveCount(int bookNo) {
		return bookStorageDAO.decreaseReserveCountByBookNo(bookNo);
	}

	@Override
	public int reserveToLoanByBookNo(int bookNo) {
		return bookStorageDAO.reserveToLoanByBookNo(bookNo);
	}

	@Override
	public List<BookStorageViewDTO> getPopularViewList() {
		return bookStorageDAO.getPopularViewList();
	}
}