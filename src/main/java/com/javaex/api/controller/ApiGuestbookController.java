package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/api/gb")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public List<GuestVo> list() {
		System.out.println(">>check ajax-list");
		
		//리스트를 불러와야해, 파일 연결하자!
		List<GuestVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList.toString());
		
		return guestbookList; //body에 data로 넣어놔
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public GuestVo add(@ModelAttribute GuestVo guestVo) {
		
		System.out.println("add");
		System.out.println(guestVo.toString());
		GuestVo vo = guestbookService.write(guestVo);
		System.out.println("controller" + vo.toString());
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public int delete(@ModelAttribute GuestVo guestVo) {
		
		int result = guestbookService.delete(guestVo);
		
		return result;
	}
	
	

}
