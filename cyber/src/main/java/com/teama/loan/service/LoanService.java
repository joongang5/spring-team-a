package com.teama.loan.service;

import java.util.List;
import java.util.Map;

import com.teama.loan.dto.LoanViewDTO;

public interface LoanService {

	public List<LoanViewDTO> getViewListByMemberNo(int memberNo);
	public List<LoanViewDTO> getViewListByMemberNo(int memberNo, int state);
	public List<Map<String, Object>> getViewMapListByMemberNo(int memberNo);
	public List<Map<String, Object>> getViewMapListByMemberNo(int memberNo, int state);
	public String loan(int bookNo, int memberNo);
	public String reserve(int bookNo, int memberNo);
	public String doReturn(int bookNo, int memberNo);
	public String autoLoan();
}
