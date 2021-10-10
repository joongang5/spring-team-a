package com.teama.storage.service;

import java.util.List;
import java.util.Map;

import com.teama.storage.dto.BookStorageDTO;
import com.teama.storage.dto.BookStorageViewDTO;

public interface BookStorageService {

	public BookStorageViewDTO getBook(int bookNo);
	public Map<String, Object> getBookMap(int bookNo);
	public List<BookStorageViewDTO> getBookList();
	public List<Map<String, Object>> getBookMapList();
	public List<Map<String, Object>> getBookMapList(int firstIndex, int recordCountPerPage);
	public List<BookStorageViewDTO> getUnregisteredBookList();
	public List<Map<String, Object>> getUnregisteredBookMapList();
	public List<Map<String, Object>> getUnregisteredBookMapList(int firstIndex, int recordCountPerPage);
	public int getTotalCount();
	public int getTotalUnregisteredCount();
	public int insertBook(BookStorageDTO dto);
	public int updateMaxCount(BookStorageDTO dto);
	public int updateMaxCount(int bookNo, int maxCount);
	public int increaseLoanCount(int bookNo);
}
