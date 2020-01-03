package com.ez.herb.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.herb.product.model.ProductService;
import com.ez.herb.product.model.ProductVO;

@Controller
@RequestMapping("/shop/product")
public class ProductController {
	private Logger logger
		=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/productByEvent.do")
	public String productByEvent(@RequestParam String eventName,
			Model model) {
		logger.info("이벤트별 상품 목록, 파라미터 eventName={}", eventName);
		
		List<ProductVO> list
			=productService.selectProductByEvent(eventName);
		logger.info("이벤트별 상품 조회 결과, list.size={}", list.size());
		
		model.addAttribute("list", list);
		
		return "shop/product/productByEvent";
	}
	
	@RequestMapping("/productDetail.do")
	public String pdDetail(@RequestParam(defaultValue = "0") 
		int productNo, Model model) {
		//1
		logger.info("상품 상세보기, 파라미터 productNo={}", productNo);
		
		if(productNo==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/index.do");
			
			return "common/message";
		}
		
		//2
		ProductVO vo=productService.selectProduct(productNo);
		logger.info("상품상세보기 결과, vo={}", vo);
				
		//3
		model.addAttribute("vo", vo);
		
		return "shop/product/productDetail";		
	}
	
	@RequestMapping("/bigImage.do")
	public void bigImage(@RequestParam String imageURL,
			@RequestParam String productName) {
		logger.info("큰 이미지 화면, 파라미터 imageURL={}, productName={}",
				imageURL, productName);		
	}
	
	@RequestMapping("/productByCategory.do")
	public void productByCategory(@RequestParam(defaultValue = "0") 
			int categoryNo,
			@RequestParam String categoryName, Model model) {
		logger.info("카테고리별 상품 조회, 파라미터 categoryNo={}, categoryName={}",
				categoryNo, categoryName);	
		
		List<ProductVO> list
			=productService.selectProductByCategory(categoryNo);
		logger.info("카테고리별 상품조회 결과, list.size={}", list.size());
		
		model.addAttribute("list", list);		
	}
}








