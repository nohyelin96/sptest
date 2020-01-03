package com.ez.herb.admin.manager.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminMybatis implements AdminDAO{
	@Autowired
	private SqlSession sqlSession;

	private String namespace="config.mybatis.mapper.oracle.admin.";
	
	@Override
	public int insertAdmin(ManagerVO vo) {
		int cnt=sqlSession.insert(namespace+"insertAdmin", vo);
		return cnt;
	}
	
	@Override
	public List<AuthorityVO> selectAdmin() {
		List<AuthorityVO> vo=sqlSession.selectList(namespace+"selectAdmin");
		return vo;
	}
}
