package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public int insert(GuestVo guestVo) {
		return guestbookDao.insert(guestVo); 
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<GuestVo> getList(){
		return guestbookDao.getList();
	}
}
