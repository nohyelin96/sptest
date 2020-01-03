package com.ez.herb.product.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOMybatis implements ProductDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.product.";
	
	@Override
	public List<ProductVO> selectProductByEvent(String eventName) {
		return sqlSession.selectList(namespace+"selectProductByEvent",
				eventName);
	}

	@Override
	public ProductVO selectProduct(int productNo) {
		return sqlSession.selectOne(namespace+"selectProduct", productNo);
	}

	@Override
	public List<ProductVO> selectProductByCategory(int categoryNo) {
		return sqlSession.selectList(namespace+"selectProductByCategory",
				categoryNo);
	}

	
}
