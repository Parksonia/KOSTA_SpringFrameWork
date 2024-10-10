package com.kosta.fac.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.fac.dto.Facility;

@Repository
public class FacilityDaoImpl implements FacilityDao {

	@Autowired
	private SqlSession sqlsession;

	@Override
	public List<Facility> selectFacilityList() throws Exception {
		
		
		return sqlsession.selectList("mapper.facility.selectAllList");
	
	}

	@Override
	public void updateFacility(Facility facility) throws Exception {
		sqlsession.update("mapper.facility.updateFacility", facility);
		
	}

	@Override
	public Facility selectFacility(String id) throws Exception {
		
		return sqlsession.selectOne("mapper.facility.selectFacility",id);
	}
}
