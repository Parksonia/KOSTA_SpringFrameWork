package com.kosta.bank.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kosta.bank.dto.Account;

public class AccountDaoImpl implements AccountDao {
	
	
	private SqlSessionTemplate sqlSession;
	
	// 생성자 방식으로 SqlSession 주입 
	public AccountDaoImpl(SqlSessionTemplate sqlSession) {
		this.sqlSession=sqlSession;
	}

	@Override
	public void insertAccount(Account acc) throws Exception {
		sqlSession.insert("mapper.account.insertAccount", acc);
		
	}

	@Override
	public Account selectAccount(String id) throws Exception {
		return sqlSession.selectOne("mapper.account.selectAccount", id);
	}

	@Override
	public void updateBalance(Account acc) throws Exception {
		sqlSession.update("mapper.account.updateBalance", acc);
		
	}

	@Override
	public List<Account> selectAllAccount() throws Exception {
		return sqlSession.selectList("mapper.account.selectAllAccount");
	}
}
