package com.kosta.fac.dto;

public class Employee {
	private String empNo;
	private String empPw;
	private String empNm;
	
	
	public Employee() {}
	

	public Employee(String empNo, String empPw, String empNm) {
		super();
		this.empNo = empNo;
		this.empPw = empPw;
		this.empNm = empNm;
	}

	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpPw() {
		return empPw;
	}
	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empPw=" + empPw + ", empNm=" + empNm + "]";
	}

	
	
	
}
