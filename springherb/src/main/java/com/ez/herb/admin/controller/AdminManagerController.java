package com.ez.herb.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.herb.admin.manager.model.AdminServiceImpl;
import com.ez.herb.admin.manager.model.AuthorityVO;
import com.ez.herb.admin.manager.model.ManagerVO;

@Controller
@RequestMapping("/admin/manager")
public class AdminManagerController {
	private static final Logger logger
	=LoggerFactory.getLogger(AdminManagerController.class);
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@RequestMapping(value="/join.do", method = RequestMethod.GET)
	public String join_get() {
		logger.info("관리자 등록 화면 보여주기");
		
		return "admin/manager/join";
	}
	
	@RequestMapping(value="/join.do", method = RequestMethod.POST)
	public String join_post(@ModelAttribute ManagerVO vo, @RequestParam String authCode, Model model ) {
		logger.info("파라미터 vo={}, authCode={}", vo);
		vo.setAuthcode(authCode);
		int cnt=adminService.insertAdmin(vo);
		String msg="", url="";
		if(cnt>0) {
			msg="관리자 등록에 성공했습니다.";
			url="/index.do";
		
		}else {
			msg="관리자 등록에 실패했습니다!";
			url="admin/manager/join.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("/authList.do")
	public String authList(Model model) {
		logger.info("관리자 전체조회 화면보이기");
		
		List<AuthorityVO> list=adminService.selectAdmin();
		logger.info("관리자 전체 조회 결과 list.size={}", list.size());
		
		model.addAttribute("list", list);
		
		return "admin/manager/authList";
	}
}
