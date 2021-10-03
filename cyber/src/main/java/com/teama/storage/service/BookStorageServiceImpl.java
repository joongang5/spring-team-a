package com.teama.storage.service;

import java.util.List;

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
	public BookStorageViewDTO getBook(int bookNo) {
		return bookStorageDAO.getBookByBookNo(bookNo);
	}

	@Override
	public List<BookStorageViewDTO> getBookList() {
		return bookStorageDAO.getBookList();
	}

	@Override
	public List<BookStorageViewDTO> getUnregisteredBookList() {
		return bookStorageDAO.getUnregisteredBookList();
	}

	@Override
	public int insertBook(BookStorageDTO dto) {
		return bookStorageDAO.insertBook(dto);
	}

	@Override
	public int updateMaxCount(BookStorageDTO dto) {
		int result = 0;
		
		BookStorageDTO resultDTO = getBook(dto.getBook_no());
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
}
