package com.kosta.board.dao;

import com.kosta.board.dto.BFile;

public interface FileDAO {

	void insertFile(BFile bfile) throws Exception;
	void deleteFile(Integer num) throws Exception;

}
