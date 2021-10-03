package com.teama.storage.service;

import java.util.List;

import com.teama.storage.dto.BookStorageDTO;
import com.teama.storage.dto.BookStorageViewDTO;

public interface BookStorageService {

	public BookStorageViewDTO getBook(int bookNo);
	public List<BookStorageViewDTO> getBookList();
	public List<BookStorageViewDTO> getUnregisteredBookList();
	public int insertBook(BookStorageDTO dto);
	public int updateMaxCount(BookStorageDTO dto);
	public int updateMaxCount(int bookNo, int maxCount);
	public int increaseLoanCount(int bookNo);
}
