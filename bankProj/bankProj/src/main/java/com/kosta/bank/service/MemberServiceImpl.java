package com.kosta.bank.service;

import com.kosta.bank.dao.MemberDao;
import com.kosta.bank.dto.Member;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao memberDao;
	public MemberServiceImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public void join(Member member) throws Exception {
		Member smember = memberDao.selectMember(member.getId());
		if(smember!=null) throw new Exception("아이디 중복 오류");
		memberDao.insertMember(member);
	}

	@Override
	public Member login(String id, String password) throws Exception {
		Member member = memberDao.selectMember(id);
		if(member==null) throw new Exception("아이디 오류");
		if(!password.equals(member.getPassword())) throw new Exception("비밀번호 오류");
		return member;
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		Member member = memberDao.selectMember(id);
		if(member==null) return false;
		return true;
	}
}
