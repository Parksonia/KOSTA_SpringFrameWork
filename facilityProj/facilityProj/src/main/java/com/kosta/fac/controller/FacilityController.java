package com.kosta.fac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.fac.dto.Facility;
import com.kosta.fac.service.EmployeeService;
import com.kosta.fac.service.FacilityService;
import com.kosta.fac.service.TeamBiz;

@Controller
public class FacilityController {

	@Autowired
	private FacilityService facilityService;
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		
		try {
			 //시설조회 
			List<Facility> list = facilityService.selectFacilities(); 
			mav.addObject("list", list);
			mav.addObject("teamBudget", new TeamBiz().selectTeamBudget());
			mav.setViewName("list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "시설 목록 조회 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	
}
