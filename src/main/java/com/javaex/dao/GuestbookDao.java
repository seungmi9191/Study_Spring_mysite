package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.vo.GuestVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public int insert(GuestVo guestVo) {
		return sqlSession.insert("guest.insert", guestVo);
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<GuestVo> getList() {
		return sqlSession.selectList("guest.list");
	}
}
