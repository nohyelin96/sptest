package com.ez.herb.zipcode.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipcodeServiceImpl implements ZipcodeService{
	@Autowired
	private ZipcodeDAO zipcodeDao;
	
	public List<ZipcodeVO> selectZipcode(ZipcodeVO vo){
		return zipcodeDao.selectZipcode(vo);
	}

	@Override
	public int selectTotalRecord(String dong) {
		return zipcodeDao.selectTotalRecord(dong);
	}
		
	
}



