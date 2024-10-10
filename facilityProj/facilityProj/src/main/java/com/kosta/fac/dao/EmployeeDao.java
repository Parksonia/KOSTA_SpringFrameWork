package com.kosta.fac.dao;

import com.kosta.fac.dto.Employee;

public interface EmployeeDao {
	void insertEmployee(Employee emp) throws Exception;
	Employee selectEmployee(String empNo) throws Exception;
}
