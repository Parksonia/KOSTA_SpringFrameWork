package com.kosta.shop.service;

import com.kosta.shop.dto.Member;

public interface MemberService {

	Member login(String userid,String passwd) throws Exception;
	boolean checkDoubleId(String userid)throws Exception;
	void signUp(Member member) throws Exception;
	void modifyMyPage(Member member) throws Exception;
	Member myPage(String userid) throws Exception;
}
