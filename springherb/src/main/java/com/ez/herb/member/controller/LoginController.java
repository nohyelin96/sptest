package com.ez.herb.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.herb.member.model.MemberService;
import com.ez.herb.member.model.MemberVO;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger
		=LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public String login_get() {
		logger.info("로그인 화면 보여주기");
		
		return "login/login";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login_post(@RequestParam String userid,
			@RequestParam String pwd, 
			@RequestParam(required = false) String chkSave,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		//1
		logger.info("로그인 처리, 파라미터 userid={}, pwd={}", userid, pwd);
		logger.info("chkSave={}", chkSave);
		
		//2
		int result=memberService.loginCheck(userid, pwd);
		String msg="", url="/login/login.do";
		if(result==MemberService.LOGIN_OK) {
			//상세정보 조회
			MemberVO vo = memberService.selectMember(userid);
			
			//세션에 저장
			HttpSession session=request.getSession();
			session.setAttribute("userid", userid);
			session.setAttribute("userName", vo.getName());
						
			//쿠키에 저장
			Cookie ck = new Cookie("ck_userid", userid);
			ck.setPath("/");
			if(chkSave!=null) {  //아이디 저장 체크한 경우
				ck.setMaxAge(1000*24*60*60);
				response.addCookie(ck);
			}else {
				ck.setMaxAge(0);  //쿠키 제거
				response.addCookie(ck);
			}
			
			msg=vo.getName() +"님 로그인되었습니다.";
			url="/index.do";
		}else if(result==MemberService.DISAGREE_PWD) {
			msg="비밀번호가 일치하지 않습니다.";
		}else if(result==MemberService.NONE_USERID) {
			msg="해당 아이디가 존재하지 않습니다.";			
		}else {
			msg="로그인 처리 실패!";			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/index.do";
	}
	
}




