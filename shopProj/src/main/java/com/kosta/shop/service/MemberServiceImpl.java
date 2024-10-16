package com.kosta.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.MemberDao;
import com.kosta.shop.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberdao;
	
	@Override
	public Member login(String userid, String passwd) throws Exception {
		
		Member loginMember = memberdao.selectMember(userid);
		
		if(loginMember == null) throw new Exception("아이디오류");
		if(!passwd.equals(loginMember.getPasswd())) throw new Exception ("비밀번호 오류");
			
		return loginMember;
	}

	@Override
	public boolean checkDoubleId(String userid) throws Exception {
		
		Member checkMember = memberdao.selectMember(userid);
		if(checkMember == null) return false;
		
		return true; // 이미 존재함 
	}

	@Override
	public void signUp(Member member) throws Exception {
		
		Member newMember = memberdao.selectMember(member.getUserid());
		if(newMember==null) {
			memberdao.insertNewMember(member);		
		}else {
			throw new Exception("계정중복");
		}
		
	}
	@Override
	public void modifyMyPage(Member member) throws Exception {
		
		// mypage.jsp에서 password value 가  userid로 되어있다. 즉 해당 유저가 아니면패스워드를 변경 할 수 없도록 하기 위함이
		if(member.getPasswd().equals(member.getUserid())) {
			member.setPasswd(null);
		}
		memberdao.updateMember(member);
		
	}

	@Override
	public Member myPage(String userid) throws Exception {		
		return memberdao.selectMember(userid);
	}
	
}
