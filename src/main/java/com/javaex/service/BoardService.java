package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao; 
	
	public List<BoardVo> bList() {
		
		return boardDao.selectList();
	}
	
	public BoardVo viewNo(int no) {
		return boardDao.selectNo(no);
	}
	
	public BoardVo modifyNo(int no) {
		return boardDao.selectModifyNo(no);
	}
	
	public int update(BoardVo boardVo) {
		
		int count = boardDao.update(boardVo);
		
		if (count>0) {
			System.out.println("수정성공");
		} else {
			System.out.println("수정실패");
		}
		
		return count;
	}
	
	public int insert(BoardVo boardVo) {
		
		int count = boardDao.insert(boardVo);
		
		if(count>0) {
			System.out.println("수정성공");
		}else {
			System.out.println("수정실패");
		}
		
		return count;
	}
	
	public int delete(int no) {
		int count = boardDao.delete(no);
		System.out.println(count);
		return count;
	}
}
