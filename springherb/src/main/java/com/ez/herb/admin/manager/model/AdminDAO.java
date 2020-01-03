package com.ez.herb.admin.manager.model;

import java.util.List;

public interface AdminDAO {
	public int insertAdmin(ManagerVO vo);
	public List<AuthorityVO> selectAdmin();
}
