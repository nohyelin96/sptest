package com.ez.herb.category.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOMybatis implements CategoryDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;

	private String namespace="config.mybatis.mapper.oracle.category.";
	
	@Override
	public List<CategoryVO> selectCategory() {
		return sqlSession.selectList(namespace+"selectCategory");
	}
	
	
}
