package com.ez.herb.product.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productDao;

	@Override
	public List<ProductVO> selectProductByEvent(String eventName) {
		return productDao.selectProductByEvent(eventName);
	}

	@Override
	public ProductVO selectProduct(int productNo) {
		return productDao.selectProduct(productNo);
	}

	@Override
	public List<ProductVO> selectProductByCategory(int categoryNo) {
		return productDao.selectProductByCategory(categoryNo);
	}

	
}
