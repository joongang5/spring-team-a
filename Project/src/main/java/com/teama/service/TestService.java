package com.teama.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.dao.TestDAO;

@Service
public class TestService {
	
	@Autowired
	private TestDAO testDAO;
	
	public List<Map<String, Object>> boardList() {
		return testDAO.boardList();
	}
}
