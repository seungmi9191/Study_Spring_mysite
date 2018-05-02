package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/guest") //value에만 해당됨
public class GuestController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		
		System.out.println(">>enter /list");
		List<GuestVo> list = guestbookService.getList();
		model.addAttribute("list", list);
		System.out.println("리스트 결과 가져옴");
		return "guestbook/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute GuestVo guestVo) {
		
		System.out.println(">>porcess /insert");
		int result = guestbookService.insert(guestVo);
		System.out.println("등록성공!" + result);
		System.out.println(guestVo.toString());
		
		return "redirect:/guest/list";	
	}
	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteform(@ModelAttribute GuestVo guestVo) {
		System.out.println(">>enter deleteform");
		//System.out.println(guestVo.toString());
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@ModelAttribute GuestVo guestVo) {
		
		System.out.println(">>process delete");
		
		int result = guestbookService.delete(guestVo);
		System.out.println(result);

		return "redirect:/guest/list";
	}
	
	@RequestMapping(value="/list-ajax", method=RequestMethod.GET)
	public String ajaxList() {
		System.out.println(">>enter list-ajax");
		return "guestbook/ajax-list";
	}
		
}
