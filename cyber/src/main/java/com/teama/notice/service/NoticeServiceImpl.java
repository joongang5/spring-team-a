package com.teama.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.notice.dao.NoticeDAO;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public List<Map<String, Object>> list() {
		return noticeDAO.selectList();
	}
	
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return noticeDAO.detail(map);
	}
}
