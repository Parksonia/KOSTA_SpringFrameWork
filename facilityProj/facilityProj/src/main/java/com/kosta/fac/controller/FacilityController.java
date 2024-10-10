package com.kosta.fac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.fac.dto.Facility;
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
			List<Facility> facilityList = facilityService.selectFacilities(); 
			
			mav.addObject("facilityList", facilityList);
			mav.addObject("teamBudget", new TeamBiz().selectTeamBudget());
			mav.setViewName("list");
		
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "시설 목록 조회 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam("id")String id) {
		ModelAndView mav = new ModelAndView();
		try {
			Facility facility = facilityService.getFaicility(id);
			mav.addObject("facility", facility);
			mav.setViewName("update");
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "시설 상세조회 오류");
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Facility facility,Model model) {
		try {
			facilityService.modifyFacility(facility);
			//@ModelAttribute를 사용하지 않는 이유는 파라미터로 받은값 중에 readOnly가 존재, 따라서 받은값 그대로 사용하는게 아니라 사용하지 않고 따로 조회.
			model.addAttribute("facility", facilityService.getFaicility(facility.getId()));
			return "update";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "시설 정보 수정 오류");
			return "error";
		}
	}
	
	
}
