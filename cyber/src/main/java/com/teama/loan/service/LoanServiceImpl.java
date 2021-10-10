package com.teama.loan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.loan.dao.LoanDAO;
import com.teama.loan.dto.LoanDTO;
import com.teama.loan.dto.LoanViewDTO;
import com.teama.storage.dao.BookStorageDAO;
import com.teama.storage.dto.BookStorageViewDTO;

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
	
	// 대출 기본 로직
	@Override
	public String loan(int bookNo, int memberNo) throws RuntimeException {
		String errorMessage = "";
		
		// 이미 대출한 책인 경우엔 대출이 불가합니다.
		LoanDTO loanDTO = loanDAO.getLoanByMemberNoAndBookNo(bookNo, memberNo);
		if (loanDTO != null)
			return "이미 대출한 책입니다.";
		
		// 책 재고가 부족하다면 대출할 수 없습니다.
		BookStorageViewDTO bookStorageViewDTO = bookStorageDAO.getBookByBookNo(bookNo);
		if (bookStorageViewDTO.isLoanable() == false)
			return "재고가 부족합니다.";
		
		int result = 0;
		// 대출 테이블에 누가 어떤 책을 대출했는지 기록합니다.  
		result = loanDAO.insertLoan(bookNo, memberNo);
		if (result <= 0)
			return "대출에 실패했습니다.";
		
		// 대출 기록에 성공하면
		// 책 저장소에 대출 카운트를 증가시킵니다.
		result = bookStorageDAO.increaseLoanCountByBookNo(bookNo);
		if (result <= 0)
			return "대출에 실패했습니다.";
		
		return errorMessage;
	}
}
