package com.javaex.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user") //value에만 해당됨
public class UserController {
	
	String url;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/joinform", method=RequestMethod.GET)
	public String joinform() {
		
		System.out.println(">>enter /joinform");
		return "user/joinform";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println(">>process /join");
		System.out.println(userVo.toString());
		userService.join(userVo);
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginform() {
		System.out.println(">>enter /loginform");
		return "user/loginform";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	//@RequestParam("파라미터 안에 있는 값의 이름") String 내가 쓸 이름
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		 
		 UserVo authUser = userService.login(email,password); //이런식으로 받아줘야함
		 
      	 //System.out.println(authUser.toString());
      	 
      	 if(authUser != null) {
      		 session.setAttribute("authUser", authUser);
      		 System.out.println(">>check Ok! /login");
      		 url = "redirect:/main";
      		 //return "redirect:/main";
      	
      	 } else {
      		url = "redirect:/user/loginform?result=fail"; 
      		System.out.println(">>check Fail! /login");
      		//return "redirect:/user/loginform";
      	 } 
      	 
      	 return url;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.removeAttribute("authUser");
		session.invalidate();
		System.out.println(">>process /logout");
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(HttpSession session, Model model) {
		UserVo authVo = (UserVo)session.getAttribute("authUser");
		UserVo userVo = userService.modifyform(authVo.getNo());
		System.out.println(">>enter /modifyform");
		System.out.println(userVo);
		model.addAttribute("userVo", userVo);
		
		return "user/modifyform";	
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		
		int result = userService.modify(userVo);
		System.out.println("DB 수정 성공반환값:" + result);
		
		if(result>0) {
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			authUser.setName(userVo.getName());
			System.out.println(">>process OK! /modify");
			//session.setAttribute("authUser", authUser);
			System.out.println(authUser);
			return "redirect:/main";
		} else {
			return "user/modifyform";
		}
	}
	
	//기존방식으로 해석 ㄴㄴ, 바디에 data넣어서 보낼테니 기존방식으로 해석하지마
	@ResponseBody //return형이 원래 .~~으로 되있는데 그거 방지
	@RequestMapping(value="/emailcheck", method=RequestMethod.POST)
	public boolean exists(@RequestParam("email") String email) {
		
		System.out.println("ajax email check reply:" + email);
		boolean isExists = userService.isEmailCheck(email);
		//boolean isExists = true;

		return isExists;
	}
}
