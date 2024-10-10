package com.kosta.fac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.fac.dao.EmployeeDao;
import com.kosta.fac.dto.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired 
	private EmployeeDao employeedao;
	
	@Override
	public void signUp(Employee employee) throws Exception {
		employeedao.insertEmployee(employee);
	
	}

	@Override
	public Employee selectOneEmployee(String empNo) throws Exception {		
		Employee emp = employeedao.selectEmployee(empNo);
		return emp;
	}

	
	@Override
	public Employee login(String empNo, String empPw) throws Exception {
		 
		 Employee loginEmp = employeedao.selectEmployee(empNo);
		if(loginEmp == null) throw new Exception("아이디오류");
		if(!empPw.equals(loginEmp.getEmpPw())) throw new Exception ("비밀번호 오류");
		 
		return loginEmp;
	}	
}
