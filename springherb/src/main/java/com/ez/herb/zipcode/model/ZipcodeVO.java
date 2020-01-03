package com.ez.herb.zipcode.model;

import com.ez.herb.common.SearchVO;

public class ZipcodeVO extends SearchVO{
	private String zipcode;
	private String sido;
	private String gugun;
	private String dong;
	private String startbunji;
	private String endbunji;
	private int seq;
		
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getStartbunji() {
		return startbunji;
	}

	public void setStartbunji(String startbunji) {
		this.startbunji = startbunji;
	}

	public String getEndbunji() {
		return endbunji;
	}

	public void setEndbunji(String endbunji) {
		this.endbunji = endbunji;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "ZipcodeVO [zipcode=" + zipcode + ", sido=" + sido + ", gugun=" + gugun + ", dong=" + dong
				+ ", startbunji=" + startbunji + ", endbunji=" + endbunji + ", seq=" + seq + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
