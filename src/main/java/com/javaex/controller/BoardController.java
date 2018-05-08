package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String boardList(Model model) {
		
		List<BoardVo> bList = boardService.bList();
		model.addAttribute("bList", bList);
		System.out.println(">>enter board_list");
		
		return "board/list";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(@RequestParam ("no") int no, Model model) {
	
		BoardVo vo = boardService.viewNo(no);
		model.addAttribute("vo", vo);
		System.out.println(vo.toString());
		System.out.println(">>success board_view");
		return "board/view";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam ("no") int no, Model model) {
		
		BoardVo Modivo = boardService.modifyNo(no);
		model.addAttribute("Modivo", Modivo);
		System.out.println(Modivo.toString());
		
		return "board/modify";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute BoardVo boardVo) {
		
		boardService.update(boardVo);
		System.out.println(boardVo.toString());
		System.out.println(">>enter board_modify_ok");
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(){
		
		return "board/write";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVo boardVo, HttpSession session) {
		
		UserVo authVo = (UserVo)session.getAttribute("authUser");
		boardVo.setUser_no(authVo.getNo()); //Board에 setUser_no에 세션 사용자 no을 넣어줘
		boardService.insert(boardVo);
		System.out.println(boardVo.toString());
		System.out.println(">>success board_write");
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam ("no") int no) {
		
		boardService.delete(no);
		System.out.println(">>success board_delete");
		
		return "redirect:/board/list";
	}
	
}
