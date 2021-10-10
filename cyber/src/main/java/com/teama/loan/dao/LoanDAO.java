package com.teama.loan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;
import com.teama.loan.dto.LoanDTO;
import com.teama.loan.dto.LoanViewDTO;

@Repository
public class LoanDAO extends AbstractDAO {

	public List<LoanViewDTO> getLoanListByMemberNo(int memberNo) {
		return sqlSession.selectList("loan.getLoanListByMemberNo", memberNo);
	}
	
	public List<Map<String, Object>> getLoanMapListByMemberNo(int memberNo) {
		return sqlSession.selectList("loan.getLoanMapListByMemberNo", memberNo);
	}
	
	public int insertLoan(int bookNo, int memberNo) {
		Map<String, Object> map = Map.of(
				"bookNo", bookNo,
				"memberNo", memberNo);
				
		return insert("loan.insertLoan", map);
	}

	public int insertReserve(int bookNo, int memberNo, String reservedLoanDate) {
		Map<String, Object> map = Map.of(
				"bookNo", bookNo,
				"memberNo", memberNo,
				"reservedLoanDate", reservedLoanDate);
				
		return insert("loan.insertReserve", map);
	}	
	
	public LoanDTO getLoanByMemberNoAndBookNo(int bookNo, int memberNo) {
		Map<String, Object> map = Map.of(
				"bookNo", bookNo,
				"memberNo", memberNo);
		
		return sqlSession.selectOne("loan.getLoanByMemberNoAndBookNo", map);	
	}

	public LoanDTO getLoanByMemberNoAndBookNo(int bookNo, int memberNo, int state) {
		Map<String, Object> map = Map.of(
				"bookNo", bookNo,
				"memberNo", memberNo,
				"state", state);
		
		return sqlSession.selectOne("loan.getLoanByMemberNoAndBookNo", map);	
	}
	
	public List<LoanDTO> getLoanByBookNo(int bookNo) {
		return sqlSession.selectList("loan.getLoanByBookNo", bookNo);
	}
}
