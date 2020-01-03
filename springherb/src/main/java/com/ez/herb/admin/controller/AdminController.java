package com.ez.herb.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger
	=LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping("/adminMain.do")
	public String adminMain() {
		logger.info("adminMain화면 보이기");
		
		return "admin/adminMain";
	}
}
