package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//Dao 입장에서 생각하기
	
	//db 입장에서는 insert
	public int insert(UserVo userVo) {
		//여기서의 insert는 이미 만들어져있는 것
		return sqlSession.insert("user.insert",userVo);
	}
	
	//Map<"key값의 자료형", 값의 자료형> int와 String이 섞인경우 Object로 넣기 
	public UserVo getUser(String email, String password) {
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("email", email);
		userMap.put("password", password);
		return sqlSession.selectOne("user.selectUserByEmailPw", userMap);
	}
	
	public UserVo userInfo(int no) {
		return sqlSession.selectOne("user.selectUserByInfo", no);
	}
	
	public int update(UserVo userVo) {
		return sqlSession.update("user.update", userVo);
	}
	
	public int getCountByEmail(String email) {
		return sqlSession.selectOne("user.selectUserByEmailCheck", email);
	}
}
