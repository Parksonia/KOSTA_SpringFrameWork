package com.kosta.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.BFile;

@Repository
public class FileDAOImpl implements FileDAO {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public void insertFile(BFile bfile) throws Exception {
		sqlSession.insert("mapper.file.insertFile", bfile);

	}

	@Override
	public void deleteFile(Integer num) throws Exception {
		sqlSession.delete("mapper.file.deleteFile",num);
	}



}
