package com.teama.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.admin.dao.AdminDAO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;

	@Override
	public List<Map<String, Object>> bookList() {
		return adminDAO.bookList();
	}

	@Override
	public List<Map<String, Object>> memberList() {
		return adminDAO.memberList();
	}

	@Override
	public Map<String, Object> searchBook(long isbn) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		// TODO Auto-generated method stub
		
		return map;
	}

	@Override
	public void registBook(long isbn) {
		// TODO Auto-generated method stub
		
	}
}
