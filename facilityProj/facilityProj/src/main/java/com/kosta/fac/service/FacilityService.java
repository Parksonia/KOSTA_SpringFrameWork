package com.kosta.fac.service;

import java.util.List;

import com.kosta.fac.dto.Facility;

public interface FacilityService {

	List<Facility> selectFacilities() throws Exception;
	
	void modifyFacility(Facility facility) throws Exception;
	
	Facility getFaicility(String id) throws Exception;
}
