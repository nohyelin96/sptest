package com.ez.herb.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.ez.herb.member.model.MemberService;
import com.ez.herb.member.model.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger
		=LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/agreement.do")
	public String agreement() {
		logger.info("agreement 화면 보여주기");
		
		return "member/agreement";
	}
	
	@RequestMapping("/register.do")
	public void register() {
		logger.info("회원가입 화면 보여주기");		
	}
	
	@RequestMapping("/memberWrite.do")
	public String write(@ModelAttribute MemberVO vo, 
			@RequestParam String email3,
			ModelMap model) {
		//1
		logger.info("회원가입 처리, 파라미터 vo={}, email3={}", vo, email3);
		
		//2
		String hp1=vo.getHp1();
		String hp2=vo.getHp2();
		String hp3=vo.getHp3();
		if(hp2==null || hp2.isEmpty() || hp3==null || hp3.isEmpty()) {
			vo.setHp1("");
			vo.setHp2("");
			vo.setHp3("");			
		}
		
		String email1=vo.getEmail1();
		String email2=vo.getEmail2();
		if(email1==null || email1.isEmpty()) {
			vo.setEmail2("");
		}else {
			if(email2.equals("etc")) {
				if(email3!=null && !email3.isEmpty()) {
					vo.setEmail2(email3);
				}else {
					vo.setEmail1("");
					vo.setEmail2("");					
				}
			}
		}
		
		logger.info("값 변경 후 vo={}", vo);
		
		int cnt=memberService.insertMember(vo);
		logger.info("회원가입 결과, cnt={}", cnt);
				
		//3
		String msg="", url="";
		if(cnt>0) {
			msg="회원가입되었습니다.";
			url="/index.do";
		}else {
			msg="회원가입 실패";
			url="/member/register.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";				
	}
	
	@RequestMapping("/checkUserid.do")
	public String checkUserid(@RequestParam String userid,
			Model model) {
		logger.info("아이디 중복확인, 파라미터 userid={}", userid);
		
		int result=0;
		
		if(userid!=null && !userid.isEmpty()) {
			result=memberService.duplicateUserid(userid);
			logger.info("아이디 중복확인 결과, result={}", result);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("EXIST_ID", MemberService.EXIST_ID);
		model.addAttribute("USEFUL_ID", MemberService.USEFUL_ID);
		
		return "member/checkUserid";
	}
	
	@RequestMapping(value="/memberEdit.do", method = RequestMethod.GET)
	public String memberEdit_get(HttpSession session, Model model) {
		String userid=(String) session.getAttribute("userid");		
		logger.info("회원수정 화면 보여주기, 파라미터 userid={}", userid);
		
		/*
		if(userid==null || userid.isEmpty()) {
			model.addAttribute("msg", "먼저 로그인하세요");
			model.addAttribute("url", "/login/login.do");
			
			return "common/message";
		}*/
		
		MemberVO vo=memberService.selectMember(userid);
		logger.info("회원정보 조회 결과, vo={}", vo);
		
		model.addAttribute("vo", vo);
		
		return "member/memberEdit";		
	}
	
	@RequestMapping(value="/memberEdit.do", method = RequestMethod.POST)
	public String memberEdit_post(@ModelAttribute MemberVO vo,
			@RequestParam(required = false) String email3,
			HttpSession session, Model model) {
		//1
		String userid=(String) session.getAttribute("userid");
		vo.setUserid(userid);
		logger.info("회원수정처리, 파라미터 vo={}, email3={}", vo, email3);
				
		//2
		String hp1=vo.getHp1();
		String hp2=vo.getHp2();
		String hp3=vo.getHp3();
		
		if(hp2==null || hp2.isEmpty() || hp3==null || hp3.isEmpty()) {
			vo.setHp1("");
			vo.setHp2("");
			vo.setHp3("");			
		}
		
		String email1=vo.getEmail1();
		String email2=vo.getEmail2();
		if(email1==null || email1.isEmpty()) {
			email1="";
			email2="";			
		}else {
			if(email2.equals("etc")) {
				if(email3!=null && !email3.isEmpty()) {
					email2=email3;
				}else {
					email1="";
					email2="";
				}
			}
		}
		vo.setEmail1(email1);
		vo.setEmail2(email2);
		
		int result=memberService.loginCheck(userid, vo.getPwd());
		
		String msg="", url="/member/memberEdit.do";
		if(result==MemberService.LOGIN_OK) {
			int cnt=memberService.updateMember(vo);
			if(cnt>0) {
				msg="회원정보 수정되었습니다.";
			}else {
				msg="회원정보 수정 실패!";
			}		
		}else if(result==MemberService.DISAGREE_PWD) {
			msg="비밀번호가 일치하지 않습니다.";
		}else {
			msg="비밀번호 체크 실패!";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/memberOut.do", method=RequestMethod.GET)
	public void memberOut_get() {
		logger.info("회원탈퇴 화면 보여주기");		
	}
	
	@RequestMapping(value="/memberOut.do", method=RequestMethod.POST)
	public String memberOut_post(@RequestParam String pwd,
			HttpSession session, Model model,
			HttpServletResponse response) {
		String userid=(String) session.getAttribute("userid");		
		logger.info("회원탈퇴, 파라미터 userid={}, pwd={}", userid, pwd);
		
		int result=memberService.loginCheck(userid, pwd);
		logger.info("비밀번호 체크 결과, result={}", result);
		
		String msg="", url="/member/memberOut.do";
		if(result==MemberService.LOGIN_OK) {
			int cnt=memberService.withdrawMember(userid);
			if(cnt>0) {
				msg="회원탈퇴 처리되었습니다.";
				url="/index.do";
				
				//session
				session.invalidate();
				
				//cookie
				Cookie ck = new Cookie("ck_userid", userid);
				ck.setPath("/");
				ck.setMaxAge(0);
				response.addCookie(ck);
			}else {
				msg="회원탈퇴 실패";
			}
		}else if(result==MemberService.DISAGREE_PWD) {
			msg="비밀번호가 일치하지 않습니다.";
		}else {
			msg="비밀번호 체크 실패!";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
}





