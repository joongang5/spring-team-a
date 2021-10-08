package com.teama.ebook.service;

import java.util.List;
import java.util.Map;

import com.teama.ebook.dto.EbookDTO;

public interface EbookService {
	//도서 검색
	public List<EbookDTO> ebookSearch(Map<String, Object> map) throws Exception;
	
	public int ebookAdd(Map<String, Object> map) throws Exception;
 	
	public List<EbookDTO> getEbookList(Map<String, Object> map);
	
	public int insertBook(EbookDTO ebookDTO);
	
	public EbookDTO getEbook(int bookNo);
	
	public EbookDTO ebookDetail(String isbn);



}
