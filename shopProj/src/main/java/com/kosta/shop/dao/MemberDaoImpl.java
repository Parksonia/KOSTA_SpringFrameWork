package com.kosta.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.shop.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSession sqlsession;

	@Override
	public Member selectMember(String userid) throws Exception {
		
		return sqlsession.selectOne("mapper.member.selectMember",userid);
	}

	@Override
	public void insertNewMember(Member member) throws Exception {
		 sqlsession.insert("mapper.member.insertMember",member);	
	}

	@Override
	public Integer idCheck(String userid) throws Exception {
		
		return sqlsession.selectOne("mapper.member.idCheck", userid);
	}

	@Override
	public void updateMember(Member member) throws Exception {
		sqlsession.update("mapper.member.updateMember", member);
		
	}
	
}
