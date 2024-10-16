package com.kosta.board.dao;

import com.kosta.board.dto.Member;

public interface MemberDAO {

	void insertMember(Member member) throws Exception;
	Member selectMember(String id) throws Exception;
	void updateMember(Member member) throws Exception;
	
}
