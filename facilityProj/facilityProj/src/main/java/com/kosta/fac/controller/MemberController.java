package com.kosta.fac.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.fac.dto.Employee;
import com.kosta.fac.service.EmployeeService;

@Controller
public class MemberController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping(value="/member/signUp",method=RequestMethod.GET)
	public String signUp() {
		return "signUp";
	}
	
	@RequestMapping(value="/member/signUp", method=RequestMethod.POST)
	public String signUp(Employee employee,Model model) {
		
		try {
			
			System.out.println(employee.getEmpNm());
			System.out.println(employee.getEmpNo());
			System.out.println(employee.getEmpPw());
			
			employeeService.signUp(employee);

			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
		
	}
	
	@RequestMapping(value="/member/login",method=RequestMethod.POST)
	public String login(@RequestParam("empNo")String empNo, @RequestParam("empPw")String empPw) {
		ModelAndView mav = new ModelAndView();
		try {
			Employee emp = employeeService.login(empNo,empPw);
			session.setAttribute("empPw", emp.getEmpPw());
			session.setAttribute("empNm", emp.getEmpNm());
			session.setAttribute("empNo", emp.getEmpNo());	
			
			return "redirect:/list";
		} catch (Exception e) {
			e.printStackTrace();
			return"error";
		}
		
		
	}
	
	
	@RequestMapping("/member/logout")
	public String logout() {
		try {
			if(session.getAttribute("empNm") !=null) {
				session.invalidate();
			}
			return "index";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
}
