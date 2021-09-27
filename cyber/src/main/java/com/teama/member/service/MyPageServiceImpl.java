package com.teama.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.ebook.dao.EbookDAO;

@Service("myPageService")
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private EbookDAO ebookDAO;
}
