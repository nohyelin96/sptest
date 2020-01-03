package com.ez.herb.board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.herb.common.SearchVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO boardDao;
		
	public int insertBoard(BoardVO vo){
		return boardDao.insertBoard(vo);
	}
	
	public List<BoardVO> selectAll(SearchVO searchVo){
		return boardDao.selectAll(searchVo);
	}
	
	public int updateReadCount(int no){
		return boardDao.updateReadCount(no);
	}
		
	public BoardVO selectByNo(int no){
		return boardDao.selectByNo(no);
	}
	
	
	public int updateBoard(BoardVO vo){
		return boardDao.updateBoard(vo);
	}

	@Override
	public boolean checkPwd(int no, String pwd) {
		String dbPwd=boardDao.selectPwd(no);
		
		if(dbPwd.equals(pwd)) {
			return true;  //비밀번호 일치
		}else {
			return false; //불일치
		}
	}
		
	public int deleteBoard(int no){
		return boardDao.deleteBoard(no);
	}

	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return boardDao.selectTotalRecord(searchVo);
	}
	
	public List<BoardVO> selectMainNotice(){
		return boardDao.selectMainNotice();
	}
	
}



