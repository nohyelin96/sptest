package com.ez.herb.zipcode.model;

import java.util.List;

public interface ZipcodeService {
	public List<ZipcodeVO> selectZipcode(ZipcodeVO vo);
	public int selectTotalRecord(String dong);
}
