package com.ez.herb.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.herb.board.model.BoardService;
import com.ez.herb.board.model.BoardVO;
import com.ez.herb.common.PaginationInfo;
import com.ez.herb.common.SearchVO;
import com.ez.herb.common.Utility;

@Controller
@RequestMapping("/board")
public class BoardListController {
	private static final Logger logger
		=LoggerFactory.getLogger(BoardListController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list.do")
	public String list(@ModelAttribute SearchVO searchVo, 
				Model model) {
		//1
		logger.info("글 목록, 파라미터 searchVo={}",searchVo);
		
		//[1] 먼저 PaginationInfo객체를 생성하여 firstRecordIndex 값을 구한다
		PaginationInfo pagingInfo=new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		
		//[2] searchVo에 recordCountPerPage와 firstRecordIndex를 셋팅한다
		searchVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		
		logger.info("값 셋팅 후 searchVo={}", searchVo);
		
		//2
		List<BoardVO> list=boardService.selectAll(searchVo);
		logger.info("글목록 결과, list.size={}", list.size());
		
		//[3] 레코드 개수 조회후 셋팅
		int totalRecord=boardService.selectTotalRecord(searchVo);
		logger.info("totalRecord={}", totalRecord);
		
		pagingInfo.setTotalRecord(totalRecord);
		
		//3
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "board/list";
	}
	
}



