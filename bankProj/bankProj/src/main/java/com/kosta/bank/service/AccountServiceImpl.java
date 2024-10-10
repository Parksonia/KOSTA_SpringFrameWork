package com.kosta.bank.service;

import java.util.List;

import com.kosta.bank.dao.AccountDao;
import com.kosta.bank.dto.Account;

public class AccountServiceImpl implements AccountService {	
	
	private AccountDao accountDao;
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public Account makeAccount(Account acc) throws Exception {
		Account sacc = accountDao.selectAccount(acc.getId());
		if(sacc!=null) throw new Exception("계좌번호 중복");
		// 계좌 DB에 저정
		accountDao.insertAccount(acc);
		return acc;
	}

	@Override
	public Account deposit(String id, Integer money) throws Exception {
		Account sacc = accountDao.selectAccount(id);
		if(sacc==null) throw new Exception("계좌번호 오류");
		sacc.deposit(money);
		accountDao.updateBalance(sacc);
		return sacc;
	}

	@Override
	public Account withdraw(String id, Integer money) throws Exception {
		Account sacc = accountDao.selectAccount(id);
		if(sacc==null) throw new Exception("계좌번호 오류");
		sacc.withdraw(money);
		accountDao.updateBalance(sacc);
		return sacc;
	}

	@Override
	public Account accountInfo(String id) throws Exception {
		Account acc = accountDao.selectAccount(id);
		if(acc==null) throw new Exception("계좌번호 오류");
		return acc;
	}

	@Override
	public List<Account> allAccountInfo() throws Exception {
		List<Account> accs = accountDao.selectAllAccount();
		return accs;
	}

	@Override
	public void transfer(String sid, String rid, Integer money) throws Exception {
		Account sacc = accountDao.selectAccount(sid);
		if(sacc==null) throw new Exception("보내는 계좌번호 오류");
		Account racc = accountDao.selectAccount(rid);
		if(racc==null) throw new Exception("받는 계좌번호 오류");
		sacc.withdraw(money);
		racc.deposit(money);
		accountDao.updateBalance(sacc);
		accountDao.updateBalance(racc);
	}

	@Override
	public boolean checkAccDoubleId(String id) throws Exception {
		Account acc = accountDao.selectAccount(id);
		if(acc==null) return false;
		return true;
	}
}