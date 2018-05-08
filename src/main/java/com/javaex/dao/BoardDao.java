package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> selectList() {
		return sqlSession.selectList("board.selectList");
	}
	
	public BoardVo selectNo(int no) {
		return sqlSession.selectOne("board.selectView", no);
	}
	
	public BoardVo selectModifyNo(int no) {
		return sqlSession.selectOne("board.selectView", no);
	}
	
	public int update(BoardVo boardVo) {
		return sqlSession.update("board.update", boardVo);
	}
	
	public int insert(BoardVo boardVo) {
		return sqlSession.insert("board.insert", boardVo);
	}
	
	public int delete(int no) {
		System.out.println("받아오는 게시물 번호: " + no);
		return sqlSession.delete("board.delete", no);
	}
}
