package com.teama.notice.service;

import java.util.List;
import java.util.Map;

public interface NoticeService {

	List<Map<String, Object>> list();

	Map<String, Object> detail(Map<String, Object> map);

}
