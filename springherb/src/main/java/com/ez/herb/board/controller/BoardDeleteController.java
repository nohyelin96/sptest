package com.ez.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.herb.board.model.BoardService;

@Controller
public class BoardDeleteController {
	private static final Logger logger
		=LoggerFactory.getLogger(BoardDeleteController.class);

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/board/delete.do", method = RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue = "0") int no,
			Model model) {
		//1
		logger.info("글 삭제 화면, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg","잘못된 url입니다.");
			model.addAttribute("url","/board/list.do");
			
			return "common/message";
		}
		
		//2
		
		//3
		return "board/delete";
	}
	
	@RequestMapping(value="/board/delete.do", method = RequestMethod.POST)
	public String delete_post(@RequestParam(defaultValue = "0") int no,
			@RequestParam String pwd, Model model) {
		//1
		logger.info("글 삭제 처리, 파라미터 no={}, pwd={}", no, pwd);
		
		//2
		String msg="", url="/board/delete.do?no="+no;
		if(boardService.checkPwd(no, pwd)) {
			int cnt=boardService.deleteBoard(no);
			if(cnt>0) {
				msg="글 삭제되었습니다.";
				url="/board/list.do";
			}else {
				msg="글 삭제 실패!";
			}
		}else {
			msg="비밀번호가 일치하지 않습니다.";
		}
		
		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
}








