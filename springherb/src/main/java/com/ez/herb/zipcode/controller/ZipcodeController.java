package com.ez.herb.zipcode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.herb.common.PaginationInfo;
import com.ez.herb.common.Utility;
import com.ez.herb.zipcode.model.ZipcodeService;
import com.ez.herb.zipcode.model.ZipcodeVO;

@Controller
@RequestMapping("/zipcode")
public class ZipcodeController {
	private static final Logger logger
		=LoggerFactory.getLogger(ZipcodeController.class);
	
	@Autowired
	private ZipcodeService zipcodeService;
	
	@RequestMapping("/zipcode.do")
	public String zipcode(@ModelAttribute ZipcodeVO zipcodeVo,
			Model model) {
		//1
		logger.info("주소찾기 파라미터, zipcodeVo={}", zipcodeVo);
		
		//2
		String dong=zipcodeVo.getDong();
		List<ZipcodeVO> list=null;
		
		PaginationInfo pagingInfo=new PaginationInfo();
		if(dong!=null && !dong.isEmpty()) {
			//페이징 처리 관련
			//[1]PaginationInfo
			pagingInfo.setBlockSize(Utility.BLOCK_SIZE);
			pagingInfo.setRecordCountPerPage(Utility.ZIPCODE_RECORD_COUNT);
			pagingInfo.setCurrentPage(zipcodeVo.getCurrentPage());
			
			//[2]SearchVo(ZipcodeVo)
			zipcodeVo.setRecordCountPerPage(Utility.ZIPCODE_RECORD_COUNT);
			zipcodeVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
						
			list=zipcodeService.selectZipcode(zipcodeVo);
			logger.info("주소찾기 결과, list.size={}", list.size());
			
			//[3]totalRecord
			int totalRecord=zipcodeService.selectTotalRecord(dong);
			logger.info("totalRecord={}", totalRecord);
			
			pagingInfo.setTotalRecord(totalRecord);
		}
		
		//3
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "zipcode/zipcode";
	}
	
	
}



