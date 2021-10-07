package com.teama.ebook.service;

import java.util.List;
import java.util.Map;

import com.teama.ebook.dto.EbookDTO;

public interface EbookAPIService {
	//도서 검색
	public List<EbookDTO> ebookSearch(Map<String, Object> map) throws Exception;
	
	public List<EbookDTO> ebookSearchKakao(String isbn) throws Exception;

	public Map<String, Object> ebookCrawler(String url) throws Exception;
	
	public int ebookAdd(Map<String, Object> map);
	
	public List<EbookDTO> searchEbook(String searchType, String searchValue, int pageNo);
}
