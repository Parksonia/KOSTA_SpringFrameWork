package com.kosta.fac.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.fac.dto.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertEmployee(Employee emp) throws Exception {
		sqlSession.insert("mapper.employee.insertEmployee", emp);

	}

	@Override
	public Employee selectEmployee(String empNo) throws Exception {
		return sqlSession.selectOne("mapper.employee.selectEmployee", empNo);
	}

}
