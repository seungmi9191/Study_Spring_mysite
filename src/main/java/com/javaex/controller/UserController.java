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
		
		System.out.println("이랏샤이마세~ joinform:)");
		return "user/joinform";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("가입됐디야_join");
		System.out.println(userVo.toString());
		userService.join(userVo);
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginform() {
		System.out.println("들어오기 전에 로그인해 loginform");
		return "user/loginform";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	//@RequestParam("파라미터 안에 있는 값의 이름") String 내가 쓸 이름
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		 
		 UserVo authUser = userService.login(email,password); //이런식으로 받아줘야함
		 
      	 //System.out.println(authUser.toString());
      	 
      	 if(authUser != null) {
      		 session.setAttribute("authUser", authUser);
      		 url = "redirect:/main";
      		 //return "redirect:/main";
      	
      	 } else {
      		url = "redirect:/user/loginform?result=fail"; 
      		//return "redirect:/user/loginform";
      	 } 
      	 
      	 return url;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(HttpSession session, Model model) {
		UserVo authVo = (UserVo)session.getAttribute("authUser");
		UserVo userVo = userService.modifyform(authVo.getNo());
		System.out.println(userVo);
		model.addAttribute("userVo", userVo);
		
		return "user/modifyform";	
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		
		int result = userService.modify(userVo);
		System.out.println(result);
		
		if(result>0) {
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			authUser.setName(userVo.getName());
			session.setAttribute("authUser", authUser);
			System.out.println(authUser);
			return "redirect:/main";
		} else {
			return "user/modifyform";
		}
	}
}
