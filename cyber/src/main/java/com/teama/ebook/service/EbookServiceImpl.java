package com.teama.ebook.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.ebook.dao.EbookDAO;
import com.teama.ebook.dto.EbookDTO;

@Service("ebookService")
public class EbookServiceImpl implements EbookService {

	@Autowired
	private EbookDAO ebookDAO;
	
	@Override
	public JSONObject ebookSearch(Map<String, Object> map) throws Exception {
		return null;
	}
	
	@Override
	public int ebookAdd(Map<String, Object> map) throws Exception {
		return ebookDAO.ebookAdd(map);
	}
	
	@Override
	public List<EbookDTO> getEbookList() {
		return ebookDAO.getEbookList();
	}

	@Override
	public int insertBook(Map<String, Object> map) {
		return ebookDAO.insert("ebook.insertBook", map);
	}
	
	@Override
	public EbookDTO getEbook(int bookNo) {
		return ebookDAO.getEbook(bookNo);
	}
}
