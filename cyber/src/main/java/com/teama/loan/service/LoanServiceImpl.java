package com.teama.loan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teama.loan.dao.LoanDAO;
import com.teama.loan.dto.LoanDTO;
import com.teama.loan.dto.LoanViewDTO;
import com.teama.storage.dao.BookStorageDAO;
import com.teama.storage.dto.BookStorageDTO;
import com.teama.storage.dto.BookStorageViewDTO;
import com.teama.util.Util;

@Service("loanService")
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanDAO loanDAO;
	@Autowired
	private BookStorageDAO bookStorageDAO;
	
	@Override
	public int totalCount(Map<String, Object> map) {
		return loanDAO.totalCount(map);
	}
	
	@Override
	public int ltotalCount(Map<String, Object> map) {
		return loanDAO.ltotalCount(map);
	}
	
	@Override
	public int reserveCount(Map<String, Object> map) {
		return loanDAO.reserveCount(map);
	}
	
	@Override
	public int todayLoanCount(int state) {
		return loanDAO.todayLoanCount(state);
	}
	
	@Override
	public LoanDTO getLoan(int no) {
		return loanDAO.getLoan(no);
	}
	
	@Override
	public List<LoanViewDTO> getViewListByMemberNo(Map<String, Object> map) {
		return loanDAO.getViewListByMemberNo(map);
	}
	
	@Override
	public List<LoanViewDTO> getViewListByMemberNo(int memberNo) {
		return loanDAO.getViewListByMemberNo(memberNo);
	}
	
	@Override
	public List<LoanViewDTO> getViewListByMemberNo(int memberNo, int state) {
		return loanDAO.getViewListByMemberNo(memberNo, state);
	}
	
	@Override
	public List<Map<String, Object>> getRecentlyViewList(int limitCount) {
		return loanDAO.getRecentlyViewList(limitCount);
	}

	@Override
	public List<LoanViewDTO> getViewPagingListByMemberNo(Map<String, Object> map) {
		return loanDAO.getViewPagingListByMemberNo(map);
	}
	
	@Override
	public List<Map<String, Object>> getViewMapListByMemberNo(int memberNo) {
		return loanDAO.getViewMapListByMemberNo(memberNo);
	}

	@Override
	public List<Map<String, Object>> getViewMapListByMemberNo(int memberNo, int state) {
		return loanDAO.getViewMapListByMemberNo(memberNo, state);
	}
	
	// 대출 기본 로직
	@Override
	public String loan(int bookNo, int memberNo) throws RuntimeException {
		// 이미 대출한 책인 경우 대출이 불가합니다.
		LoanDTO loanDTO = loanDAO.getLoanByMemberNoAndBookNo(bookNo, memberNo, LoanState.loan.getValue());
		if (loanDTO != null)
			return "이미 대출한 책입니다.";
		
		// 이미 예약한 책인 경우 예약이 불가합니다.
		LoanDTO reservedloanDTO = loanDAO.getLoanByMemberNoAndBookNo(bookNo, memberNo, LoanState.reserve.getValue());
		if (reservedloanDTO != null)
			return "이미 예약한 책입니다.";
		
		// 책 재고가 부족하다면 대출할 수 없습니다.
		BookStorageViewDTO bookStorageViewDTO = bookStorageDAO.getViewByBookNo(bookNo);
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

		// 히스토리 테이블에도 기록합니다.
		result = loanDAO.insertLoanHistory(bookNo, memberNo, LoanState.loan.getValue());
		if (result <= 0)
			return "대출에 실패했습니다.";
		
		return "";
	}

	// 예약 기본 로직
	@Override
	public String reserve(int bookNo, int memberNo) {
		// 이미 대출한 책인 경우 예약이 불가합니다.
		LoanDTO loanDTO = loanDAO.getLoanByMemberNoAndBookNo(bookNo, memberNo, LoanState.loan.getValue());
		if (loanDTO != null)
			return "이미 대출한 책입니다.";	
				
		// 이미 예약한 책인 경우 예약이 불가합니다.
		LoanDTO reservedloanDTO = loanDAO.getLoanByMemberNoAndBookNo(bookNo, memberNo, LoanState.reserve.getValue());
		if (reservedloanDTO != null)
			return "이미 예약한 책입니다.";		

		// 예약이 다 차면 예약할 수 없습니다.
		BookStorageViewDTO bookStorageViewDTO = bookStorageDAO.getViewByBookNo(bookNo);
		if (bookStorageViewDTO.isReservable() == false)
			return "예약이 모두 찼습니다.";
		
		// 대출 예정일을 계산합니다.
		// 대출된 목록 중 예약 대기 순서와 일치하는 것의 반납 날짜를 기준으로 결정됩니다.
		String reservedLoanDate;
		// 사용자의 예약 버튼은 모든 대출이 나간 이후에 활성화 되지만 안전을 위해 체크합니다.
		if (bookStorageViewDTO.isLoanable()) {
			// 대출이 가능한 상태였다면 현재 시간으로 예약시킵니다.
			reservedLoanDate = Util.getStrCurrentTime();
		} else {
			// 반납 시간이 빠른 순서대로 리스트를 얻어옵니다.
			List<LoanDTO> loanDTOList = loanDAO.getLoanByBookNo(bookNo, LoanState.loan.getValue());
			// 현재 대출이 모두 나간 상태, 예약이 다 차지 않은 상태입니다.
			// 인덱스 접근이기 때문에 -1 계산합니다.
            LoanDTO reservableLoanDTO = loanDTOList.get(bookStorageViewDTO.getReserve_count() - 1);
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

		// 히스토리 테이블에도 기록합니다.
		result = loanDAO.insertLoanHistory(bookNo, memberNo, LoanState.reserve.getValue());
		if (result <= 0)
			return "예약에 실패했습니다.";
		
		return "";
	}

	// 반납 기본 로직
	@Override
	public String doReturn(int no) {
		LoanDTO loanDTO = loanDAO.getLoan(no);
		if (loanDTO == null)
			return "대출 정보를 찾을 수 없습니다.";
		
		// 대출 상태를 loan->returned로 변경합니다.
		loanDTO.setState(LoanState.returned.getValue());
		// 대출 당시에는 반납 예정일로 사용했던 return_date를 실제 반납 완료일로 변경합니다.  
		loanDTO.setReturn_date(Util.getStrCurrentTime());

		int result = 0;
		// 수정 사항을 DB에 반영합니다.
		result = loanDAO.updateLoanByNo(loanDTO);
		if (result <= 0)
			return "반납 처리에 실패했습니다.";

		// 반납된 책에 대한 대출 카운트를 감소시킵니다.
		result = bookStorageDAO.decreaseLoanCountByBookNo(loanDTO.getBook_no());
		if (result <= 0)
			return "반납 처리에 실패했습니다.";
		
		// 히스토리 테이블에도 기록합니다.
		result = loanDAO.insertLoanHistory(loanDTO.getBook_no(), loanDTO.getMember_no(), LoanState.returned.getValue());
		if (result <= 0)
			return "반납 처리에 실패했습니다.";
		
		return "";
	}

	// 연장 기본 로직
	@Override
	public String doExtend(int no) {
		LoanDTO loanDTO = loanDAO.getLoan(no);
		if (loanDTO == null)
			return "대출 정보를 찾을 수 없습니다.";
		 
		// 예약이 하나라도 걸려있다면 연장할 수 없습니다.
		BookStorageDTO bookStorageDTO = bookStorageDAO.getBook(loanDTO.getBook_no());
		if (bookStorageDTO.getReserve_count() > 0)
			return "예약이 걸려있어 연장할 수 없습니다.";
		
		int additiveDay = 1;
		loanDTO.setReturn_date(Util.getStrAdditiveTime(loanDTO.getReturn_date(), additiveDay));

		int result = 0;
		result = loanDAO.updateLoanByNo(loanDTO);
		if (result <= 0)
			return "연장 처리에 실패했습니다.";
		
		// 히스토리 테이블에도 기록합니다.
		result = loanDAO.insertLoanHistory(loanDTO.getBook_no(), loanDTO.getMember_no(), LoanState.extend.getValue());
		if (result <= 0)
			return "연장 처리에 실패했습니다.";
		
		return "";
	}
	
	@Override
	public String autoLoan() {
		List<BookStorageDTO> needAutoLoanList = bookStorageDAO.getNeedAutoLoanList();
		for (BookStorageDTO bookStorageDTO : needAutoLoanList) {
			int tryCount = bookStorageDTO.getMax_count() - bookStorageDTO.getLoan_count();
			
			List<LoanDTO> loanDTOList = loanDAO.getLoanListByBookNo(bookStorageDTO.getBook_no(), LoanState.reserve.getValue());
			for (int i = 0; i < loanDTOList.size(); i++) {
				if (i >= tryCount)
					break;
				
				LoanDTO reservedLoanDTO = loanDTOList.get(i);
				reservedLoanDTO.setState(LoanState.loan.getValue());
				reservedLoanDTO.setLoan_date(Util.getStrCurrentTime());
				reservedLoanDTO.setReturn_date(Util.getStrCurrentTime(5));
				
				int result = 0;
				result = loanDAO.updateLoanByNo(reservedLoanDTO);
				if (result <= 0)
					return "자동 대출 처리에 실패했습니다.";
				
				result = bookStorageDAO.reserveToLoanByBookNo(reservedLoanDTO.getBook_no());
				if (result <= 0)
					return "자동 대출 처리에 실패했습니다.";
				
				// 히스토리 테이블에도 기록합니다.
				result = loanDAO.insertLoanHistory(reservedLoanDTO.getBook_no(), reservedLoanDTO.getMember_no(), LoanState.loan.getValue());
				if (result <= 0)
					return "자동 대출 처리에 실패했습니다.";
			}	
		}
		
		return "";
	}

	@Override
	public String autoReturn() {
		List<LoanDTO> needAutoReturnList = loanDAO.getNeedAutoReturnList();
		for (LoanDTO loanDTO : needAutoReturnList) {
			loanDTO.setState(LoanState.returned.getValue());
			loanDTO.setReturn_date(Util.getStrCurrentTime());
			
			int result = 0;
			result = loanDAO.updateLoanByNo(loanDTO);
			if (result <= 0)
				return "자동 반납 처리에 실패했습니다.";
			
			result = bookStorageDAO.decreaseReserveCountByBookNo(loanDTO.getBook_no());
			if (result <= 0)
				return "자동 반납 처리에 실패했습니다.";

			// 히스토리 테이블에도 기록합니다.
			result = loanDAO.insertLoanHistory(loanDTO.getBook_no(), loanDTO.getMember_no(), LoanState.returned.getValue());
			if (result <= 0)
				return "자동 반납 처리에 실패했습니다.";
		}
		
		return "";
	}
}
