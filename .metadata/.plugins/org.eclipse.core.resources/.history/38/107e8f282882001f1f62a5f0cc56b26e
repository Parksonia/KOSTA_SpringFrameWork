package dao;

import org.apache.ibatis.session.SqlSession;

import dto.Member;
import utils.MybatisSqlSessionFactory;

public class MemberDAOImpl implements MemberDAO {

	// MybatisSqlSessionFactory를 통해 SqlSession 생성
		SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
		
	
	//DAO에서는 mapper파일과 연결 시켜주는 역할은 하는 것 
	@Override
	public void insertMember(Member member) throws Exception {
		Member smember = selectMember(member.getId());
		if(smember !=null) throw new Exception("아이디 중복 오류");
		
		sqlSession.insert("mapper.member.insertMember",member);
		sqlSession.commit(); //insert
	}

	@Override
	public Member selectMember(String id) throws Exception {
	
		return sqlSession.selectOne("mapper.member.selectMember",id);
	}

	@Override
	public void updateMember(Member member) throws Exception {
	  sqlSession.update("mapper.member.updateMember",member);
	  sqlSession.commit();
	}

}
