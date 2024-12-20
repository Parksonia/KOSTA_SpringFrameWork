package com.kosta.board.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.board.dto.Member;
import com.kosta.board.service.MemberService;



@Controller
public class MemberController {

	//servlet-context.xml에서 이미 beans로 interface-impl 등록 했음 
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="login",method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value="login",method = RequestMethod.POST)
	public String login(@RequestParam("id")String id,@RequestParam("password")String password,
						@RequestParam(value="type",required=false)String autoLogin,HttpServletResponse response,Model model) {

		if(autoLogin != null) {
			Cookie cookieId = new Cookie("id",id);
			cookieId.setMaxAge(60*60*24);
			response.addCookie(cookieId);
			
			Cookie cookiePW = new Cookie("password",password);
			cookiePW.setMaxAge(60*60*24);
			response.addCookie(cookiePW);
			
			Cookie cookieAutoLogin = new Cookie("autologin",autoLogin);
			cookieAutoLogin.setMaxAge(60*60*24);
			response.addCookie(cookieAutoLogin);

		}else { // 이미 쿠키가 있으면 삭제 하는 작업 실행
			Cookie cookieId = new Cookie("id",id);
			cookieId.setMaxAge(0);
			response.addCookie(cookieId);
			
			Cookie cookiePW = new Cookie("password",password);
			cookiePW.setMaxAge(0);
			response.addCookie(cookiePW);
			
			Cookie cookieAutoLogin = new Cookie("autologin",autoLogin);
			cookieAutoLogin.setMaxAge(0);
			response.addCookie(cookieAutoLogin);
			
		}
		
		try {
			Member member = memberService.login(id, password); 
			
			 member.setPassword("");
		     session.setAttribute("member", member); // session에 'member' 속성값 저장

			return "redirect:boardList"; // 데이터를 싣고보내야하니까 boardList url로 다시 요청
						
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err",e.getMessage());
			return "err";
		}
			
	}
	
	@RequestMapping(value="join")
	public String join() {
		return "join";
	}
	@RequestMapping(value="join",method = RequestMethod.POST)
	public String join(Member member,Model model) {
		
		try {
			memberService.join(member);			
			return "login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err",e.getMessage());
			return "err";
		}
		
	
	}
	//ajax 의 중복체크 처리 위한 mapping (Model을 주지 않음)
	//@ResponseBody  : view가 아니라 데이터를 준다는 의미 
	@RequestMapping(value="memberDoubldId",method=RequestMethod.POST)
	@ResponseBody
	public String memberDoubleId(@RequestParam("id")String id) {

		try {
			boolean check  = memberService.checkDoubleId(id);
			return String.valueOf(check);
				
		} catch (Exception e) {
			e.printStackTrace(); 
			return e.getMessage();
		}

	}
	@RequestMapping(value="logout")
	public String logout(Member member) {
		
		try {
			session.removeAttribute("member");
			//session.invalidate();
			return "redirect:login";
		
		} catch (Exception e) {
			e.printStackTrace();
			return "err";
		}
	
	}
	
	
	
}
