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
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public int delete(GuestVo guestVo) {
		
		int count = guestbookDao.delete(guestVo);
		
		if(count>0) {
			count = guestVo.getNo();//이 번호로 찾아서 지운다.
			System.out.println(count);
			System.out.println("삭제성공");
		} else {
			count = 0;
			System.out.println(count);
			System.out.println("삭제실패");
		}
		return count;
	}
	
	
	public GuestVo write(GuestVo guestVo) {
		
		//insert
		int no = guestbookDao.insert2(guestVo); 
		// guestbookDao.insert2(guestVo); 
		
		//select
		return guestbookDao.selectGuestBook(no);
		//return guestbookDao.selectGuestBook(vo.getNo());
	}
}