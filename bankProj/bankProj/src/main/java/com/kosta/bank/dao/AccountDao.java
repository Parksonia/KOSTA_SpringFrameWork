package com.kosta.bank.dao;

import java.util.List;

import com.kosta.bank.dto.Account;

public interface AccountDao {
	void insertAccount(Account acc) throws Exception;
	Account selectAccount(String id) throws Exception;
	void updateBalance(Account acc) throws Exception;
	List<Account> selectAllAccount() throws Exception;
}
