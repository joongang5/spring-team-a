package com.teama.storage.service;

import java.util.List;
import java.util.Map;

import com.teama.storage.dto.BookStorageDTO;
import com.teama.storage.dto.BookStorageViewDTO;

public interface BookStorageService {

	public BookStorageDTO getBook(int bookNo);

	public Map<String, Object> getBookMap(int bookNo);

	public BookStorageViewDTO getView(int bookNo);

	public Map<String, Object> getViewMap(int bookNo);

	public List<BookStorageDTO> getBookList();

	public List<BookStorageDTO> getNeedAutoLoanList();

	public List<BookStorageViewDTO> getViewList();

	public List<BookStorageViewDTO> getViewList(String title, int firstIndex, int recordCountPerPage);

	public List<Map<String, Object>> getViewMapList();

	public List<Map<String, Object>> getViewMapList(int firstIndex, int recordCountPerPage);

	public List<BookStorageViewDTO> getUnregisteredViewList();

	public List<Map<String, Object>> getUnregisteredViewMapList();

	public List<Map<String, Object>> getUnregisteredViewMapList(int firstIndex, int recordCountPerPage);

	public int getTotalCount();

	public int getTotalCount(String title);

	public int getTotalUnregisteredCount();

	public int insertBook(BookStorageDTO dto);

	public int insertBookList(List<Map<String, Object>> dtoList);

	public int updateMaxCount(BookStorageDTO dto);

	public int updateMaxCount(int bookNo, int maxCount);

	public int increaseLoanCount(int bookNo);

	public int increaseReserveCount(int bookNo);

	public int decreaseLoanCount(int bookNo);

	public int decreaseReserveCount(int bookNo);

	public int reserveToLoanByBookNo(int bookNo);

	public List<BookStorageViewDTO> getPopularViewList();
}
