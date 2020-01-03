package com.ez.herb.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ez.herb.board.model.BoardService;
import com.ez.herb.board.model.BoardVO;

@Controller
public class BoardController {
	private static final Logger logger
		=LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/notice/mainNotice.do")
	public String mainNotice(Model model) {
		//1
		logger.info("메인 공지사항");
		
		//2
		List<BoardVO> list=boardService.selectMainNotice();
		logger.info("메인 공지사항 조회 결과, list.size={}", list.size());
		
		//3
		model.addAttribute("list", list);
		
		return "inc/mainNotice";
	}
	
}
