package com.ez.herb.admin.manager.model;

import java.sql.Timestamp;

public class AuthorityVO {
	private String authcode;
	private String authname;
	private String authdesc;
	private int authlevel;
	private Timestamp regdate;
	
	public AuthorityVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthorityVO(String authcode, String authname, String authdesc, int authlevel, Timestamp regdate) {
		super();
		this.authcode = authcode;
		this.authname = authname;
		this.authdesc = authdesc;
		this.authlevel = authlevel;
		this.regdate = regdate;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getAuthname() {
		return authname;
	}

	public void setAuthname(String authname) {
		this.authname = authname;
	}

	public String getAuthdesc() {
		return authdesc;
	}

	public void setAuthdesc(String authdesc) {
		this.authdesc = authdesc;
	}

	public int getAuthlevel() {
		return authlevel;
	}

	public void setAuthlevel(int authlevel) {
		this.authlevel = authlevel;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "ManagerVO [authcode=" + authcode + ", authname=" + authname + ", authdesc=" + authdesc + ", authlevel="
				+ authlevel + ", regdate=" + regdate + "]";
	}
}
