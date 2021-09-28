package com.teama.admin.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

	public List<Map<String, Object>> bookList();
	public List<Map<String, Object>> memberList();
}
