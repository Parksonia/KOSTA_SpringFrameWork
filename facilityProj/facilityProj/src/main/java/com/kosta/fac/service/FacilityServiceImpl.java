package com.kosta.fac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.fac.dao.FacilityDao;
import com.kosta.fac.dto.Facility;

@Service
public class FacilityServiceImpl implements FacilityService {
	
	
	@Autowired
	private FacilityDao facilitydao;

	@Override
	public List<Facility> selectFacilities() throws Exception {
	
		return facilitydao.selectFacilityList();
	}

	@Override
	public void modifyFacility(Facility facility) throws Exception {
		facilitydao.updateFacility(facility);
		
	}

	@Override
	public Facility getFaicility(String id) throws Exception {
		
		return facilitydao.selectFacility(id);
	}

}
