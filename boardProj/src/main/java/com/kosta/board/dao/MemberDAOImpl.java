package com.kosta.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.Member;

// @Repository 어노테이션 추가 해서 injection
@Repository 
public class MemberDAOImpl implements MemberDAO {

	
	
	/* 이전 방식
	 * private SqlSession sqlSession;
	 * 
	 * public MemberDAOImpl(SqlSession sqlSession) { this.sqlSession = sqlSession; }
	 */
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//DAO에서는 mapper파일과 연결 시켜주는 역할은 하는 것 
	@Override
	public void insertMember(Member member) throws Exception {
		Member smember = selectMember(member.getId());
		if(smember !=null) throw new Exception("아이디 중복 오류");
		
		sqlSession.insert("mapper.member.insertMember",member);
		
	}

	@Override
	public Member selectMember(String id) throws Exception {
	
		return sqlSession.selectOne("mapper.member.selectMember",id);
	}

	@Override
	public void updateMember(Member member) throws Exception {
	  sqlSession.update("mapper.member.updateMember",member);
	
	}

}
