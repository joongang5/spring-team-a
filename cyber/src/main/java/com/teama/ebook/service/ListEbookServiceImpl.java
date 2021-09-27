package com.teama.ebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.ebook.dao.EbookDAO;

@Service("listEbookService")
public class ListEbookServiceImpl implements ListEbookService {

	@Autowired
	private EbookDAO ebookDAO;
}
