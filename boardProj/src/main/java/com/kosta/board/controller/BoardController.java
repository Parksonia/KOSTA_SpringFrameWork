package com.kosta.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.Member;
import com.kosta.board.service.BoardService;
import com.kosta.board.utils.PageInfo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/boardList")
	public ModelAndView boardList(@RequestParam (value="page" ,required=false,defaultValue = "1")Integer page) {
		// 파라미터 name=page를 가져오고 기본값을 1로 지정 , required = false 값이 없으면 404에러   
		ModelAndView mav = new ModelAndView();
	
		try {
		
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			List<Board> boardList = boardService.boardList(pageInfo);
			mav.addObject("boardList", boardList);
			mav.addObject("pageInfo",pageInfo);
			mav.setViewName("boardlist");
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err",e.getMessage());
			mav.setViewName("err");			
		}
		return mav;
	}
	
	@RequestMapping("/boardDetail")
	public ModelAndView boardDetail(@RequestParam("num")Integer num) {
		ModelAndView mav = new ModelAndView();
		Member member = (Member)session.getAttribute("member");
		System.out.println(member.getId());
		
		try {
			Board board = boardService.boardDetail(num);
			
			mav.addObject("board", board);
			
			if(member != null) {
				Integer hnum = boardService.checkHeart(member.getId(), num); 
				
				System.out.println(hnum);
				mav.addObject("heart", hnum);
			}
			mav.addObject("member", member);
			
			mav.setViewName("boarddetail");
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		
		return mav;
	}
	

	
	@RequestMapping(value="/boardWrite",method=RequestMethod.GET)
	public String boardWrite() {		
		return "writeform";
	}
	//@RequestPart 파라미터를 따로 가져오고 싶을 때 사용
	@RequestMapping(value="/boardWrite",method=RequestMethod.POST)
	public String boardWrite(@ModelAttribute Board board,
							 @RequestPart(value="file",required=false)MultipartFile file,
							 @RequestPart(value="dfile",required=false)MultipartFile dfile,Model model) {
		ModelAndView mav = new ModelAndView();
		try {
			System.out.println(board);
			boardService.boardWrite(board, file, dfile);
			return "redirect:boardDetail?num="+board.getNum();
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());	
			return "err";
		}
		
	}
	
	@RequestMapping(value="/modifyboard", method=RequestMethod.GET)
	public ModelAndView boardModify(@RequestParam("num")Integer num) {
			ModelAndView mav = new ModelAndView();
			try {
				Board board = boardService.boardDetail(num);
				mav.addObject("board", board);
				mav.setViewName("modifyform");
			} catch (Exception e) {
				mav.addObject("err", e.getMessage());
				mav.setViewName("err");
			}
		
		return mav;
	}
	
	@RequestMapping(value="/modifyboard",method=RequestMethod.POST)
	public String boardModify(@ModelAttribute Board board,
							  @RequestPart(value="file",required=false)MultipartFile file,
							  @RequestPart(value="dfile",required=false)MultipartFile dfile,Model model) {

		try {
			System.out.println(board.getFilename());
			
			boardService.boardModify(board,file,dfile);
			return "redirect:boardDetail?num="+board.getNum();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "err";
		}

	}
	
	@RequestMapping(value="/heart",method=RequestMethod.POST)
	@ResponseBody
	public String heart(@RequestParam("num") Integer num) {
		try {
			boolean heart = boardService.toggleHeart(((Member)session.getAttribute("member")).getId(), num);
			return String.valueOf(heart);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	@RequestMapping(value="/fileDown") //{filename} ==PahthVariable과 같아야함
	public String fileDown(@RequestParam("file")String filename, HttpServletResponse response) {
		
		try {
			String path = "";
			File file = new File(filename);// path일단 뺌
			response.setContentType("application/download");
			response.setContentLength((int)file.length());
			response.setHeader("Content-disposition", "attachment;filename=\""+filename+"\"");
			OutputStream out = response.getOutputStream();
			FileInputStream fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
			fis.close();
			
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
			
		}
	}
	
}
