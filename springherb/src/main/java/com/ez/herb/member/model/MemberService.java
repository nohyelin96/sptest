package com.ez.herb.member.model;

public interface MemberService {
	//아이디 중복확인
	public static final int EXIST_ID=1;  //아이디가 이미 존재하는 경우
	public static final int USEFUL_ID=2;  //아이디가 사용 가능한 경우
	
	//로그인 처리
	public static final int LOGIN_OK=1;  //로그인 성공
	public static final int DISAGREE_PWD=2; //비밀번호 불일치
	int NONE_USERID=3; //해당 아이디 존재하지 않음
		
	int insertMember(MemberVO vo);
	public int duplicateUserid(String userid);
	public int loginCheck(String userid, String pwd);
	public MemberVO selectMember(String userid);
	public int updateMember(MemberVO vo);
	public int withdrawMember(String userid);
	
	
	
}
