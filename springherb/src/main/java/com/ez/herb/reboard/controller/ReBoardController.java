package com.ez.herb.reboard.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ez.herb.common.FileUploadUtil;
import com.ez.herb.common.PaginationInfo;
import com.ez.herb.common.SearchVO;
import com.ez.herb.common.Utility;
import com.ez.herb.reboard.model.ReBoardService;
import com.ez.herb.reboard.model.ReBoardVO;

@Controller
@RequestMapping("/reBoard")
public class ReBoardController {
	private static final Logger logger
	=LoggerFactory.getLogger(ReBoardController.class); 

	@Autowired
	private ReBoardService reBoardService;

	@Autowired
	private FileUploadUtil fileUtil;
	
	@RequestMapping(value="/write.do", method=RequestMethod.GET)
	public String write_get() {
		logger.info("글쓰기 화면");

		return "reBoard/write";
	}

	@RequestMapping(value="/write.do", method = RequestMethod.POST)
	public String write_post(@ModelAttribute ReBoardVO reBoardVo, 
			HttpServletRequest request, Model model) {
		//1
		logger.info("글등록, 파라미터 vo={}",reBoardVo);

		//파일 업로드
		List<Map<String, Object>> list=fileUtil.fileUpload(request);
		String fileName="", originalFileName="";
		long fileSize=0;
		
		for(Map<String, Object> map: list) {
			originalFileName=(String) map.get("originalFileName");
			fileName=(String) map.get("fileName");
			fileSize=(Long) map.get("fileSize");			
		}//for
		
		reBoardVo.setOriginalFileName(originalFileName);
		reBoardVo.setFileName(fileName);
		reBoardVo.setFileSize(fileSize);
		
		//2
		String msg="", url="";
		int cnt=reBoardService.insertReBoard(reBoardVo);
		logger.info("글등록 결과, cnt={}", cnt);

		if(cnt>0) {
			msg="글등록되었습니다.";
			url="/reBoard/list.do";
		}else {
			msg="글등록 실패!";
			url="/reBoard/write.do";
		}

		//3		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";
	}

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
		List<ReBoardVO> list=reBoardService.selectAll(searchVo);
		logger.info("글목록 결과, list.size={}", list.size());
		
		//[3] 레코드 개수 조회후 셋팅
		int totalRecord=reBoardService.selectTotalRecord(searchVo);
		logger.info("totalRecord={}", totalRecord);
		
		pagingInfo.setTotalRecord(totalRecord);
		
		//3
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "reBoard/list";
	}
	
	@RequestMapping("/countUpdate.do")
	public String countUpdate(@RequestParam(defaultValue = "0") int no,
			Model model) {
		logger.info("조회수 증가, 파라미터 no={}", no);
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			
			return "common/message";
		}
		
		int cnt=reBoardService.updateReadCount(no);
		logger.info("조회수 증가 결과, cnt={}", cnt);
		
		return "redirect:/reBoard/detail.do?no="+no;		
	}
	
	@RequestMapping("/detail.do")
	public String detail(@RequestParam(defaultValue = "0") int no,
			Model model) {
		logger.info("상세보기 파라미터, no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			
			return "common/message";
		}
		
		ReBoardVO reBoardVo=reBoardService.selectByNo(no);
		logger.info("상세보기 결과, vo={}", reBoardVo);
		
		String fileInfo
			=Utility.getFileInfo(reBoardVo.getOriginalFileName(), 
				reBoardVo.getFileSize());
		
		model.addAttribute("vo", reBoardVo);
		model.addAttribute("fileInfo", fileInfo);
		
		return "reBoard/detail";
	}
	
	@RequestMapping(value="/edit.do", method =RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue = "0") int no,
			Model model) {
		logger.info("수정화면 파라미터 no={}", no);		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			
			return "common/message";
		}
		
		ReBoardVO reBoardVo=reBoardService.selectByNo(no);
		logger.info("수정화면 결과, reBoardVo={}", reBoardVo);
		
		model.addAttribute("reBoardVo", reBoardVo);
		
		return "reBoard/edit";
	}
	
	@RequestMapping(value="/edit.do", method = RequestMethod.POST)
	public String edit_post(@ModelAttribute ReBoardVO reBoardVo, 
			@RequestParam String oldFileName,
			HttpServletRequest request,	Model model) {
		//1
		logger.info("글 수정 처리, 파라미터 vo={}, oldFileName={}", 
				reBoardVo, oldFileName);
		
		//2
		String msg="", url="/reBoard/edit.do?no="+reBoardVo.getNo();
		if(reBoardService.checkPwd(reBoardVo.getNo(), reBoardVo.getPwd())) {
			//[1] file upload
			List<Map<String, Object>> list=fileUtil.fileUpload(request);
			
			String fileName="", originalFileName="";
			long fileSize=0;
			for(Map<String, Object> map : list) {
				fileName=(String) map.get("fileName");
				originalFileName=(String) map.get("originalFileName");
				fileSize=(Long) map.get("fileSize");
			}
			
			reBoardVo.setFileName(fileName);
			reBoardVo.setOriginalFileName(originalFileName);
			reBoardVo.setFileSize(fileSize);
			
			//[2] db
			int cnt=reBoardService.updateReBoard(reBoardVo);
			if(cnt>0) {
				msg="글 수정되었습니다.";
				url="/reBoard/detail.do?no="+reBoardVo.getNo();
				
				//[3] 기존 file delete
				if(!list.isEmpty()) {  //새로 업로드 된 경우
					if(oldFileName!=null && !oldFileName.isEmpty()) {
						//기존파일이 있는 경우
						String path=fileUtil.getFilePath(request);
						File oldFile = new File(path, oldFileName);
						if(oldFile.exists()) {
							boolean bool=oldFile.delete();
							logger.info("기존 파일 삭제 여부:{}", bool);	
						}
					}
				}//if
			}else {
				msg="글 수정 실패!";
			}
		}else {
			msg="비밀번호가 일치하지 않습니다.";
		}
		
		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue = "0") int no,
			Model model) {
		//1
		logger.info("글 삭제 화면, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg","잘못된 url입니다.");
			model.addAttribute("url","/reBoard/list.do");
			
			return "common/message";
		}
		
		//2
		
		//3
		return "reBoard/delete";
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String delete_post(@ModelAttribute ReBoardVO vo,
			HttpServletRequest request, Model model) {
		//1
		logger.info("글 삭제 처리, 파라미터 vo={}", vo);
		
		//2
		String msg="", url="/reBoard/delete.do?no="+vo.getNo()
			+"&fileName="+vo.getFileName()+"&groupNo="+vo.getGroupNo()
			+"&step="+vo.getStep();
		if(reBoardService.checkPwd(vo.getNo(), vo.getPwd())) {
			Map<String, String> map=new HashMap<String, String>();
			map.put("no", vo.getNo()+"");
			map.put("groupNo", vo.getGroupNo()+"");
			map.put("step", vo.getStep()+"");
						
			reBoardService.deleteReBoard(map);
			
			msg="글 삭제되었습니다.";
			url="/reBoard/list.do";
			
			//파일이 첨부된 경우 파일 삭제 처리
			if(vo.getFileName()!=null && !vo.getFileName().isEmpty()) {
				String path=fileUtil.getFilePath(request);
				File file = new File(path , vo.getFileName());
				if(file.exists()) {
					boolean bool=file.delete();
					logger.info("파일 삭제 여부:{}", bool);
				}
			}		
			
		}else {
			msg="비밀번호가 일치하지 않습니다.";
		}
		
		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}

	@RequestMapping("/download.do")
	public ModelAndView download(@RequestParam(defaultValue = "0") int no,
			@RequestParam String fileName,
			HttpServletRequest request) {
		//1
		logger.info("다운로드 처리, 파라미터 no={}, fileName={}", no, fileName);
		
		//2
		int cnt=reBoardService.updateDownCount(no);
		logger.info("다운로드수 증가, 결과 cnt={}", cnt);			
		
		//3
		String upPath=fileUtil.getFilePath(request);
		logger.info("uppath={}, filename={}", upPath, fileName);
		
		File file = new File(upPath, fileName);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file", file);
		
		//ModelAndView(String viewName, Map<String, ?> model)
		ModelAndView mav 
			= new ModelAndView("reBoardDownloadView", map);
		
		return mav;
	}
	
	@RequestMapping(value="/reply.do", method = RequestMethod.GET)
	public String reply_get(@RequestParam(defaultValue = "0") int no,
			ModelMap model) {
		//1
		logger.info("답변하기 화면, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			
			return "common/message";
		}
		
		//2
		ReBoardVO vo=reBoardService.selectByNo(no);
		logger.info("답변하기 화면 결과 vo={}",vo);
		
		//3
		model.addAttribute("vo", vo);
		
		return "reBoard/reply";
	}
	
	@RequestMapping(value="/reply.do", method=RequestMethod.POST)
	public String reply_post(@ModelAttribute ReBoardVO vo,
			ModelMap model) {
		//1
		logger.info("답변하기 파라미터, vo={}", vo);
		
		//2
		int cnt=reBoardService.reply(vo);
		logger.info("답변하기 결과, cnt={}", cnt);
		
		//3
		if(cnt>0) {
			return "redirect:/reBoard/list.do";
		}else {
			model.addAttribute("msg", "답변하기 실패!");
			model.addAttribute("url", "/reBoard/reply.do?no="+vo.getNo());
			
			return "common/message";
		}
	}
	
	
}




