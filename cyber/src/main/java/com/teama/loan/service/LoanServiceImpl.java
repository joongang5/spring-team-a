package com.teama.loan.service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		// 이미 대출한 책인 경우 대출이 불가합니다.
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
		
		return "";
	}

	// 예약 기본 로직
	@Override
	public String reserve(int bookNo, int memberNo) {
		// 이미 예약한 책인 경우 예약이 불가합니다.
		LoanDTO loanDTO = loanDAO.getLoanByMemberNoAndBookNo(bookNo, memberNo, LoanState.reserve.getValue());
		if (loanDTO != null)
			return "이미 예약한 책입니다.";		

		// 예약이 다 차면 예약할 수 없습니다.
		BookStorageViewDTO bookStorageViewDTO = bookStorageDAO.getBookByBookNo(bookNo);
		if (bookStorageViewDTO.isReservable() == false)
			return "예약이 모두 찼습니다.";
		
		// 대출 예정일을 계산합니다.
		// 대출된 목록 중 예약 대기 순서와 일치하는 것의 반납 날짜를 기준으로 결정됩니다.
		String reservedLoanDate;
		// 예약은 모든 대출이 나간 이후에 가능하지만 테스트를 위해 체크합니다.
		if (bookStorageViewDTO.getLoan_count() <= bookStorageViewDTO.getReserve_count()) {
			Date currentTime = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			reservedLoanDate = format.format(currentTime);
		} else {
			List<LoanDTO> loanDTOList = loanDAO.getLoanByBookNo(bookNo);
			LoanDTO reservableLoanDTO = loanDTOList.get(bookStorageViewDTO.getReserve_count());
			reservedLoanDate = reservableLoanDTO.getReturn_date();	
		}
		
		int result = 0;
		// 예약을 실행합니다.
		result = loanDAO.insertReserve(bookNo, memberNo, reservedLoanDate);
		if (result <= 0)
			return "예약에 실패했습니다.";

		// 예약 기록에 성공하면
		// 책 저장소에 예약 카운트를 증가시킵니다.
		result = bookStorageDAO.increaseReserveCountByBookNo(bookNo);
		if (result <= 0)
			return "예약에 실패했습니다.";
		
		return "";
	}
}
