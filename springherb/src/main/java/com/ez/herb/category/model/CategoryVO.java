package com.ez.herb.category.model;

public class CategoryVO {
	private int categoryNo;
    private String categoryName;
    private int categoryOrder;
    
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryOrder() {
		return categoryOrder;
	}
	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}
	
	@Override
	public String toString() {
		return "CategoryVO [categoryNo=" + categoryNo + ", categoryName=" + categoryName + ", categoryOrder="
				+ categoryOrder + "]";
	}
    
	
	
    
}
