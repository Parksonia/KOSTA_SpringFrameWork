package com.kosta.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.board.dao.MemberDAO;
import com.kosta.board.dao.MemberDAOImpl;
import com.kosta.board.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDao;
	
	/* 구버전
	 * private MemberDAO memberDao; 
	 * public MemberServiceImpl(MemberDAO memberDao) {
	 * this.memberDao = memberDao; }
	 */
	@Override
	public void join(Member member) throws Exception {
		
		//selectmember로 중복 검증 추가해야함 

		Member smember = new Member();
		smember=memberDao.selectMember(member.getId());
		
		if(smember==null) {
			memberDao.insertMember(member);
		}else {
			throw new Exception("계정 중복");
		}

	}

	@Override
	public Member login(String id, String password) throws Exception {
	
		Member lmember = memberDao.selectMember(id); // 찾은 멤버를 담을 객체
		
		if(lmember == null) throw new Exception("아이디 오류");
		if(!password.equals(lmember.getPassword())) throw new Exception("패스워드 오류"); 
		
		return lmember;
	}
	
	@Override
	public boolean checkDoubleId(String id) throws Exception {

		Member member = memberDao.selectMember(id);
		if(member==null) return false;
		
		return true;
	}

}
