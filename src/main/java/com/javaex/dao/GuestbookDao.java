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
	
	
	public int insert(GuestVo guestVo) {
		return sqlSession.insert("guest.insert", guestVo);
	}
	
	
	public List<GuestVo> getList() {
		return sqlSession.selectList("guest.list");
	}
	
	
	public int delete(GuestVo guestVo) {
		return sqlSession.delete("guest.delete", guestVo);
	}
	
	//추가됨
	public GuestVo selectGuestBook(int no) {
		return sqlSession.selectOne("guest.selectGuestBook", no);
	}
	
	//여기서의 int는 getNo key값의 int
	public int insert2(GuestVo guestVo) {
		System.out.println(guestVo.toString());
		sqlSession.insert("guest.insert2", guestVo); //반환된건 한줄 자체가 반환형 int 성공여부(0 또는 1)(뒤에 gusetVo는 앞에 service에서 받아온 값)
		System.out.println(guestVo.toString());
		return guestVo.getNo(); //굳이 guestVo.getNo();안해도  vo안에 no값이 있으니까..
	}
	
}
