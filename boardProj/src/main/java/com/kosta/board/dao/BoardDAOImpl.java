package com.kosta.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private SqlSessionTemplate sqlSession;
	public BoardDAOImpl(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insertBoard(Board board) throws Exception {
		
		sqlSession.insert("mapper.board.insertBoard",board);
		//sqlSession.commit(); springframework에서는 commit하면 에러
	}

	@Override
	public Board selectBoard(Integer num) throws Exception {
		
		return sqlSession.selectOne("mapper.board.selectBoard",num);
	}

	@Override
	public void updateBoard(Board board) throws Exception {
		sqlSession.update("mapper.board.updateBoard",board);

	}

	@Override
	public List<Board> selectBoardList(Integer row) throws Exception {
		return sqlSession.selectList("mapper.board.selectBoardList",row-1);
	}

	@Override
	public void updateViewCnt(Integer num) throws Exception {
		sqlSession.update("mapper.board.updateViewCnt",num);

	}

	@Override
	public Integer selectBoardCount() throws Exception {
		
		return sqlSession.selectOne("mapper.board.selectBoardCount");
	}

}
