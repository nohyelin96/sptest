package com.ez.herb.product.model;

import java.util.List;

public interface ProductService {
	List<ProductVO> selectProductByEvent(String eventName);
	ProductVO selectProduct(int productNo);
	List<ProductVO> selectProductByCategory(int categoryNo);
	
	
}
