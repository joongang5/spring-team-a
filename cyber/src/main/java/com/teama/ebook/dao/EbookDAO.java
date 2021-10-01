package com.teama.ebook.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;

@Repository
public class EbookDAO extends AbstractDAO{
	public int ebookAdd(Map<String, Object> map) {		
		return insert("ebook.ebookAdd", map);
	}
}
