package com.teama.loan.service;

import java.util.List;
import java.util.Map;

import com.teama.loan.dto.LoanDTO;
import com.teama.loan.dto.LoanViewDTO;

public interface LoanService {

	public LoanDTO getLoan(int no);
	public List<LoanViewDTO> getViewListByMemberNo(Map<String, Object> map);
	public List<LoanViewDTO> getViewListByMemberNo(int memberNo);
	public List<LoanViewDTO> getViewListByMemberNo(int memberNo, int state);
	public List<Map<String, Object>> getRecentlyViewList(int limitCount);
	public List<LoanViewDTO> getViewPagingListByMemberNo(Map<String, Object> map);
	public List<Map<String, Object>> getViewMapListByMemberNo(int memberNo);
	public List<Map<String, Object>> getViewMapListByMemberNo(int memberNo, int state);
	public String loan(int bookNo, int memberNo);
	public String reserve(int bookNo, int memberNo);
	public String doReturn(int no);
	public String doExtend(int no);
	public String autoLoan();
	public String autoReturn();
	public int totalCount(Map<String, Object> map);
	public int ltotalCount(Map<String, Object> map);
	public int reserveCount(Map<String, Object> map);
	public int todayLoanCount(int state);
}
