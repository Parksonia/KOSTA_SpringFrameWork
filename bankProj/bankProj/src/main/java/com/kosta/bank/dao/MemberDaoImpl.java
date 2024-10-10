package com.kosta.bank.dao;


import org.mybatis.spring.SqlSessionTemplate;
import com.kosta.bank.dto.Member;

public class MemberDaoImpl implements MemberDao {
	
	private SqlSessionTemplate sqlSession;
	
	//setter 방식으로 주입
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertMember(Member member) throws Exception {
		Member smember = selectMember(member.getId());
		if(smember!=null) throw new Exception("아이디 중복 오류");
		sqlSession.insert("mapper.member.insertMember", member);
		//springFrame에서 commit필요 없다.
	}

	@Override
	public Member selectMember(String id) throws Exception {
		return sqlSession.selectOne("mapper.member.selectMember",id);
	}

	@Override
	public void updateMember(Member member) throws Exception {
		sqlSession.update("mapper.member.updateMember", member);
		
	}
}