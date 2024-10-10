package com.kosta.fac.service;

import com.kosta.fac.dto.Employee;

public interface EmployeeService {

	void signUp(Employee employee)throws Exception;
	Employee selectOneEmployee(String empNo) throws Exception;
	Employee login(String empNo,String empPw) throws Exception;
}
