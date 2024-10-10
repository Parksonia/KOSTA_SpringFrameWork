package com.kosta.bank.service;

import java.util.List;

import com.kosta.bank.dto.Account;

public interface AccountService {
	Account makeAccount(Account acc) throws Exception;
	Account deposit(String id, Integer money) throws Exception;
	Account withdraw(String id, Integer money) throws Exception;
	Account accountInfo(String id) throws Exception;
	List<Account> allAccountInfo() throws Exception;
	void transfer(String sid, String rid, Integer money) throws Exception;
	
	boolean checkAccDoubleId(String id) throws Exception;
}
