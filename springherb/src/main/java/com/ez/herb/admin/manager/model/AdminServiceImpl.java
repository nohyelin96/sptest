package com.ez.herb.admin.manager.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDAO adminDao;
	
	@Override
	public int insertAdmin(ManagerVO vo) {
		return adminDao.insertAdmin(vo);
	}
	
	@Override
	public List<AuthorityVO> selectAdmin() {
		return adminDao.selectAdmin();
	}
}
