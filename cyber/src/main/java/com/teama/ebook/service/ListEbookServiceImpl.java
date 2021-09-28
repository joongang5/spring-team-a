package com.teama.ebook.service;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.ebook.dao.EbookDAO;

@Service("listEbookService")
public class ListEbookServiceImpl implements ListEbookService {

	@Autowired
	private EbookDAO ebookDAO;

	@Override
	public JSONObject ebookSearch(Map<String, Object> map) throws Exception {
		return ebookDAO.ebookSearch(map);
	}
	
}
