package com.ez.herb.member.model;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDao;
			
	public int insertMember(MemberVO vo) {
		int cnt=memberDao.insertMember(vo);
		return cnt;
	}
	
	
	public int duplicateUserid(String userid) {
		int result=0;
		
		int count=memberDao.duplicateUserid(userid);
		if(count>0) {
			result=EXIST_ID; //해당 아이디 이미 존재
		}else {
			result=USEFUL_ID; //사용가능
		}
		
		return result;
	}
	
	public int loginCheck(String userid, String pwd){
		String dbPwd = memberDao.selectPwd(userid);
		
		int result=0;
		if(dbPwd==null || dbPwd.isEmpty()) {
			result=NONE_USERID;  //해당 아이디가 없다
		}else {
			if(dbPwd.equals(pwd)) {
				result=LOGIN_OK;  //로그인 성공
			}else {
				result=DISAGREE_PWD; //비밀번호 불일치
			}
		}
		
		return result;
	}
		
	public MemberVO selectMember(String userid){
		return memberDao.selectMember(userid);
	}
			
	public int updateMember(MemberVO vo){
		return memberDao.updateMember(vo);
	}
	
	public int withdrawMember(String userid) {
		return memberDao.withdrawMember(userid);
	}
}









