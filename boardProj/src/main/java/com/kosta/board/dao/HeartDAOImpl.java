package com.kosta.board.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HeartDAOImpl implements HeartDAO {

	@Autowired
	private SqlSession sqlSession; 
	
	
	@Override
	public void insertHeart(String memberId, Integer boardNum) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("mem_id", memberId);
		param.put("board_num", boardNum);
		sqlSession.insert("mapper.heart.insertHeart",param);
		
	}

	@Override
	public Integer selectHeart(String memberId, Integer boardNum) throws Exception {
		Map<String,Object> param =new HashMap<>();
		param.put("mem_id", memberId);
		param.put("board_num", boardNum);
		
		return sqlSession.selectOne("mapper.heart.selectHeart",param);
	}

	@Override
	public void deleteHeart(String memberId, Integer boardNum) throws Exception {
		Map<String,Object> param =new HashMap<>();
		param.put("mem_id", memberId);
		param.put("board_num", boardNum);
		
		sqlSession.delete("mapper.heart.deleteHeart",param);
		
	}

}
