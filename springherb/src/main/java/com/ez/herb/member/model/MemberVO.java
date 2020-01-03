package com.ez.herb.member.model;

import java.sql.Timestamp;

public class MemberVO {
	private int no;
    private String userid;
    private String name;
    private String pwd;
    private String email1;
    private String email2;
    private String hp1;
    private String hp2;
    private String hp3;    
    private String zipcode;
    private String address;
    private String addressDetail;
    private Timestamp regdate;
    private int mileage;
    private Timestamp outdate;
    
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
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getHp1() {
		return hp1;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	public String getHp2() {
		return hp2;
	}
	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}
	public String getHp3() {
		return hp3;
	}
	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public Timestamp getOutdate() {
		return outdate;
	}
	public void setOutdate(Timestamp outdate) {
		this.outdate = outdate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [no=" + no + ", userid=" + userid + ", name=" + name + ", pwd=" + pwd + ", email1=" + email1
				+ ", email2=" + email2 + ", hp1=" + hp1 + ", hp2=" + hp2 + ", hp3=" + hp3 + ", zipcode=" + zipcode
				+ ", address=" + address + ", addressDetail=" + addressDetail + ", regdate=" + regdate + ", mileage="
				+ mileage + ", outdate=" + outdate + "]";
	}
    
	
	
    
}
