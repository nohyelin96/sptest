package com.ez.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ez.herb.board.model.BoardService;
import com.ez.herb.board.model.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardWriteController {
	private static final Logger logger
		=LoggerFactory.getLogger(BoardWriteController.class); 
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/write.do", method=RequestMethod.GET)
	public String write_get() {
		logger.info("글쓰기 화면");
		
		return "board/write";
	}
	
	@RequestMapping(value="/write.do", method = RequestMethod.POST)
	public String write_post(@ModelAttribute BoardVO boardVo, Model model) {
		//1
		logger.info("글등록, 파라미터 vo={}",boardVo);
		
		//2
		String msg="", url="";
		int cnt=boardService.insertBoard(boardVo);
		logger.info("글등록 결과, cnt={}", cnt);
		
		if(cnt>0) {
			msg="글등록되었습니다.";
			url="/board/list.do";
		}else {
			msg="글등록 실패!";
			url="/board/write.do";
		}
		
		//3		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
}





