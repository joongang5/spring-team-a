package com.teama.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.teama.dao.AbstractDAO;
import com.teama.member.dto.MemberDTO;

@Repository
public class MemberDAO extends AbstractDAO {

	public Map<String, Object> login(Map<String, Object> map) {
		return selectOne("member.login", map);
	}

	public int join(Map<String, Object> map) {
		return insert("member.join", map);
	}

	public String isUsableId(String id) {
		return sqlSession.selectOne("member.idCheck", id);
	}

	public String isUsableEmail(String email) {
		return sqlSession.selectOne("emailCheck", email);
	}

	public int todayJoinCount() {
		return sqlSession.selectOne("member.todayJoinCount");
	}
	
	public String findId(String email) {
		return sqlSession.selectOne("member.findId", email);
	}

	public String findPw(String id) {
		return sqlSession.selectOne("member.findPw", id);
	}

	public MemberDTO getMemberByNo(int no) {
		return sqlSession.selectOne("member.getMemberByNo", no);
	}

	public MemberDTO getMemberById(String id) {
		return sqlSession.selectOne("member.getMemberById", id);
	}
	
	public List<MemberDTO> getMemberList() {
		return sqlSession.selectList("member.getMemberList");
	}

	public List<MemberDTO> getRecentlyMemberList(int limitCount) {
		return sqlSession.selectList("member.getRecentlyMemberList", limitCount);
	}
	
	public List<MemberDTO> getMemberListByNo(int no) {
		return sqlSession.selectList("member.getMemberByNo", no);
	}

	public List<MemberDTO> memberListById(String id) {
		return sqlSession.selectList("member.getMemberById", id);
	}

	public List<MemberDTO> memberListByName(String name) {
		return sqlSession.selectList("member.getMemberByName", name);
	}

	public List<MemberDTO> memberListByEmail(String email) {
		return sqlSession.selectList("member.getMemberByEmail", email);
	}

	public List<MemberDTO> memberListByJoinDate(String joinDate) {
		return sqlSession.selectList("member.getMemberByJoinDate", joinDate);
	}

	public int memberUpdate(MemberDTO memberDTO) {
		return sqlSession.update("member.memberUpdate", memberDTO);
	}

	public String getSalt(String id) {
		return sqlSession.selectOne("member.getSalt", id);
	}

	public String encryptPw(Map<String, Object> map) {
		return sqlSession.selectOne("member.encryptPw", map);
	}
}