package com.kosta.fac.dao;

import java.util.List;

import com.kosta.fac.dto.Facility;

public interface FacilityDao {

	List<Facility> selectFacilityList() throws Exception;
	void updateFacility(Facility facility) throws Exception;
}
