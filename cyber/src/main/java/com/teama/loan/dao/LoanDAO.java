package com.teama.loan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;
import com.teama.loan.dto.LoanDTO;
import com.teama.loan.dto.LoanViewDTO;

@Repository
public class LoanDAO extends AbstractDAO {

	public List<LoanViewDTO> getViewListByMemberNo(Map<String, Object> map) {
		return sqlSession.selectList("loan.getViewListByMemberNo", map);
	}
	
	public List<LoanViewDTO> getViewListByMemberNo(int memberNo) {
		Map<String, Object> map = Map.of(
				"memberNo", memberNo);
		
		return sqlSession.selectList("loan.getViewListByMemberNo", map);
	}
	
	public List<LoanViewDTO> getViewListByMemberNo(int memberNo, int state) {
		Map<String, Object> map = Map.of(
				"memberNo", memberNo,
				"state", state);
		
		return sqlSession.selectList("loan.getViewListByMemberNo", map);
	}

	public List<LoanViewDTO> getViewPagingListByMemberNo(Map<String, Object> map) {
		return sqlSession.selectList("loan.getViewPagingListByMemberNo", map);
	}
	
	public List<Map<String, Object>> getViewMapListByMemberNo(int memberNo) {
		return sqlSession.selectList("loan.getViewMapListByMemberNo", memberNo);
	}
	
	public List<Map<String, Object>> getViewMapListByMemberNo(int memberNo, int state) {
		Map<String, Object> map = Map.of(
				"memberNo", memberNo,
				"state", state);
		
		return sqlSession.selectList("loan.getViewMapListByMemberNo", map);
	}
	
	public List<LoanDTO> getLoanListByBookNo(int bookNo, int state) {
		Map<String, Object> map = Map.of(
				"bookNo", bookNo,
				"state", state);
		
		return sqlSession.selectList("loan.getLoanListByBookNo", map);
	}

	public int insertLoan(int bookNo, int memberNo) {
		Map<String, Object> map = Map.of(
				"bookNo", bookNo,
				"memberNo", memberNo);
				
		return insert("loan.insertLoan", map);
	}
	
	public int extendLoan(LoanDTO dto) {
		return sqlSession.update("loan.extendLoan", dto);
	}

	public int insertReserve(int bookNo, int memberNo, String reservedLoanDate) {
		Map<String, Object> map = Map.of(
				"bookNo", bookNo,
				"memberNo", memberNo,
				"reservedLoanDate", reservedLoanDate);
				
		return insert("loan.insertReserve", map);
	}	
	
	public int updateLoanByNo(LoanDTO dto) {
		return sqlSession.update("loan.updateLoanByNo", dto);
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
	
	public List<LoanDTO> getLoanByBookNo(int bookNo, int state) {
		Map<String, Object> map = Map.of(
				"bookNo", bookNo,
				"state", state);
		
		return sqlSession.selectList("loan.getLoanByBookNo", map);
	}

	public List<LoanDTO> getNeedAutoReturnList() {
		return sqlSession.selectList("loan.getNeedAutoReturnList");
	}
	
	public int totalCount(Map<String, Object> map) {
		return Integer.parseInt(String.valueOf (selectOne("loan.totalCount", map).get("totalCount")));
	}
	
	public int ltotalCount(Map<String, Object> map) {
		return Integer.parseInt(String.valueOf (selectOne("loan.ltotalCount", map).get("ltotalCount")));
	}

	public int reserveCount(Map<String, Object> map) {
		return Integer.parseInt(String.valueOf (selectOne("loan.reserveCount", map).get("reserveCount")));
	}

}
