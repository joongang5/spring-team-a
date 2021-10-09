package com.teama.loan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.loan.dao.LoanDAO;
import com.teama.loan.dto.LoanDTO;
import com.teama.loan.dto.LoanViewDTO;
import com.teama.storage.dao.BookStorageDAO;

@Service("loanService")
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanDAO loanDAO;
	@Autowired
	private BookStorageDAO bookStorageDAO;
	
	@Override
	public List<LoanViewDTO> getLoanListByMemberNo(int memberNo) {
		return loanDAO.getLoanListByMemberNo(memberNo);
	}

	@Override
	public List<Map<String, Object>> getLoanMapListByMemberNo(int memberNo) {
		return loanDAO.getLoanMapListByMemberNo(memberNo);
	}
	
	@Override
	public boolean loan(int bookNo, int memberNo) {
		int result = 0;
		
		LoanDTO loanDTO = loanDAO.getLoanByMemberNoAndBookNo(bookNo, memberNo);
		if (loanDTO != null)
			return false;
		
		result = loanDAO.insertNewLoanData(bookNo, memberNo);
		if (result <= 0)
			return false;
		
		result = bookStorageDAO.increaseLoanCountByBookNo(bookNo);
		if (result <= 0)
			return false;
		
		return true;
	}
}
