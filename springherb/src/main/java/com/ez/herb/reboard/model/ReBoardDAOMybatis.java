package com.ez.herb.reboard.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.herb.common.SearchVO;

@Repository
public class ReBoardDAOMybatis implements ReBoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.reboard.";
	
	public int insertReBoard(ReBoardVO vo) {
		int cnt=sqlSession.insert(namespace+"insertReBoard", vo);
		return cnt;
	}
	
	public List<ReBoardVO> selectAll(SearchVO searchVo){
		List<ReBoardVO> list
			=sqlSession.selectList(namespace+"selectAll", searchVo);
		
		return list;
	}
	
	public int updateReadCount(int no){
		int cnt=sqlSession.update(namespace+"updateReadCount", no);
		return cnt;
	}
	
	public ReBoardVO selectByNo(int no){
		ReBoardVO vo=sqlSession.selectOne(namespace+"selectByNo", no);
		return vo;
	}
	
	public int updateReBoard(ReBoardVO vo){
		int cnt=sqlSession.update(namespace+"updateReBoard", vo);
		return cnt;
	}

	@Override
	public String selectPwd(int no) {
		String pwd=sqlSession.selectOne(namespace+"selectPwd", no);
		return pwd;
	}
	
	public void deleteReBoard(Map<String, String> map) {
		sqlSession.delete(namespace+"deleteReBoard", map);
	}

	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return sqlSession.selectOne(namespace+"selectTotalRecord",
				searchVo);
	}

	@Override
	public int updateDownCount(int no) {
		return sqlSession.update(namespace+"updateDownCount", no);
	}

	@Override
	public int updateSortNo(ReBoardVO vo) {
		return sqlSession.update(namespace+"updateSortNo", vo);
	}

	@Override
	public int reply(ReBoardVO vo) {
		return sqlSession.insert(namespace+"reply", vo);
	}
	
	
	
/*
	public List<ReBoardVO> selectMainNotice() throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<ReBoardVO> list=new ArrayList<ReBoardVO>();		
		try {
			con=pool.getConnection();
			
			String sql="select *" + 
					" from" + 
					" (" + 
					"    select * from reBoard order by no desc" + 
					" )" + 
					" where rownum<=6";
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				int no=rs.getInt("no");
				int readcount=rs.getInt("readcount");
				String name=rs.getString("name");
				String title=rs.getString("title");
				String pwd=rs.getString("pwd");
				String email=rs.getString("email");
				String content=rs.getString("content");
				Timestamp regdate=rs.getTimestamp("regdate");
				
				ReBoardVO vo = new ReBoardVO(no, name, pwd, title, email, 
						regdate, readcount, content);
				
				list.add(vo);
			}
			System.out.println("최근 공지사항 조회 결과 list.size="
					+list.size());
			
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	*/
}








