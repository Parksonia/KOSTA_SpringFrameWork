package com.kosta.shop.dao;

import com.kosta.shop.dto.Member;

public interface MemberDao {

	Member selectMember(String userid) throws Exception; // 아이디 중복체크에도 활용함
	
	void insertNewMember(Member member) throws Exception;
	
	Integer idCheck(String userid) throws Exception;
	
	void updateMember(Member member) throws Exception;
}
