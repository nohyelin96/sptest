package com.ez.herb.category.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDAO categoryDao;

	@Override
	public List<CategoryVO> selectCategory() {
		return categoryDao.selectCategory();
	}
	
	
}
