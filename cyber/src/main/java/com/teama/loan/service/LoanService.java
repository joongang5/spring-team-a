package com.teama.loan.service;

import java.util.List;
import java.util.Map;

import com.teama.loan.dto.LoanViewDTO;

public interface LoanService {

	public List<LoanViewDTO> getLoanListByMemberNo(int memberNo);
	public List<Map<String, Object>> getLoanMapListByMemberNo(int memberNo);
	public boolean loan(int bookNo, int memberNo);
}
