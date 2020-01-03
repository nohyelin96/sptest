package com.ez.herb.admin.manager.model;

import java.sql.Timestamp;

public class ManagerVO {
	private int no;
	private String userid;
	private String name;
	private String pwd;
	private String authcode;
	private Timestamp regdate;
	
	public ManagerVO() {
		super();
	}

	public ManagerVO(int no, String userid, String name, String pwd, String authcode, Timestamp regdate) {
		super();
		this.no = no;
		this.userid = userid;
		this.name = name;
		this.pwd = pwd;
		this.authcode = authcode;
		this.regdate = regdate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "AuthorityVO [no=" + no + ", userid=" + userid + ", name=" + name + ", pwd=" + pwd + ", authcode="
				+ authcode + ", regdate=" + regdate + "]";
	}
}
