package com.ez.herb.board.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.herb.common.SearchVO;

@Repository
public class BoardDAOMybatis implements BoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.board.";
	
	public int insertBoard(BoardVO vo) {
		int cnt=sqlSession.insert(namespace+"insertBoard", vo);
		return cnt;
	}
	
	public List<BoardVO> selectAll(SearchVO searchVo){
		List<BoardVO> list
			=sqlSession.selectList(namespace+"selectAll", searchVo);
		
		return list;
	}
	
	public int updateReadCount(int no){
		int cnt=sqlSession.update(namespace+"updateReadCount", no);
		return cnt;
	}
	
	public BoardVO selectByNo(int no){
		BoardVO vo=sqlSession.selectOne(namespace+"selectByNo", no);
		return vo;
	}
	
	public int updateBoard(BoardVO vo){
		int cnt=sqlSession.update(namespace+"updateBoard", vo);
		return cnt;
	}

	@Override
	public String selectPwd(int no) {
		String pwd=sqlSession.selectOne(namespace+"selectPwd", no);
		return pwd;
	}
	
	public int deleteBoard(int no) {
		return sqlSession.delete(namespace+"deleteBoard", no);
	}

	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return sqlSession.selectOne(namespace+"selectTotalRecord",
				searchVo);
	}
	
	public List<BoardVO> selectMainNotice(){
		return sqlSession.selectList(namespace+"selectMainNotice");
	}
	
}








