package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	
	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		return userDao.insert(userVo);
	}
	
	public UserVo login(String email, String password) {
		return userDao.getUser(email, password);
	}
	
	public UserVo modifyform(int no) {
		return userDao.userInfo(no);
	}
	
	public int modify(UserVo userVo) {
		return userDao.update(userVo);
	}
	

}
