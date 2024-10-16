package com.kosta.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;

//servlet프로젝트와 다르게 하나의 corolller에 여러개의 메서드로 urlmapping이 가능하다.
@Controller
public class AccountController {
	
	//@Autowired : 의존관계 주입
	@Autowired
	private AccountService accountService;
	
	
	//url mapping -> 받을 데이터가 없으면 parameter 추가 안하면 됨  
	// view는 String type이다. 
	@RequestMapping(value="accountInfo", method=RequestMethod.GET)
	public String accountInfo() {
		return "accountinfoform";
	}
	
	
	//url mapping ->parameter가 있을 경우 해당 메서드에 파라미터로 넣어준다. 
	//파라미터가 있기때문에 오버로딩으로 같은 메서드명을 사용 할 수 있는 것 
	// Model = service통해 데이터를 가져온걸 담아줌
	@RequestMapping(value="accountInfo", method=RequestMethod.POST)
	public String accountInfo(@RequestParam("id") String id, Model model) {
		try {
			Account acc = accountService.accountInfo(id);
			model.addAttribute("acc", acc);
			return "accountinfo";
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "계좌조회 오류");
			return "err";
		}
	}
	@RequestMapping(value="deposit",method = RequestMethod.GET)
	public String deposit() {
		return "deposit"; //jsp view로 연결
	}
	
	
	//jsp는 Strng Type
	//Model객체에 담아서 view로 연결할 때 는 ModelAndView 객체타입 으로 메서드 설정
	@RequestMapping(value="deposit",method = RequestMethod.POST)
	public ModelAndView deposit(@RequestParam("id")String id ,@RequestParam("money")Integer money) {
		ModelAndView mv = new ModelAndView();
		 try {
			Account acc = accountService.deposit(id, money);
			 mv.addObject("acc",acc);
			 mv.setViewName("accountinfo");
		} catch (Exception e) {
			mv.addObject("err",e.getMessage());
			mv.setViewName("err");
		}
		
		return mv; //model에 담아서 view로 연결할 수도 있다.
	}

	@RequestMapping(value="withdraw",method = RequestMethod.GET)
	public String withdraw() {
		return "withdraw"; //jsp view로 연결
	}
	
//parameter방식 2 - 기본 타입별로 받기 @RequestParam("id")String id 
//	@RequestMapping(value="withdraw",method = RequestMethod.POST)
//	public ModelAndView withdraw(@RequestParam("id")String id ,@RequestParam("money")Integer money) {
//		ModelAndView mv = new ModelAndView();
//		 try {
//			Account acc = accountService.withdraw(id, money);
//			 mv.addObject("acc",acc);
//			 mv.setViewName("accountinfo");
//		} catch (Exception e) {
//			mv.addObject("err",e.getMessage());
//			mv.setViewName("err");
//		}
//		
//		return mv; //model에 담아서 view로 연결할 수도 있다.
//	}
//	
	//parameter 방식 3 -Map으로 받기 (parameter가 굉장히 많은 경우 활용하기 좋음)
	@RequestMapping(value="withdraw",method = RequestMethod.POST)
	public String withdraw(@RequestParam Map<String,String>param,Model model) {
		
		 try {
			String id = param.get("id");
			Integer money = Integer.parseInt(param.get("money"));
			Account acc = accountService.withdraw(id, money);
			model.addAttribute("acc", acc);
			return "accountinfo";
			
		 } catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("err", "계좌조회 오류");
				return "err";
		}
		
	}
	
	//parameter방식 4 -ServletRequest로 받기 
	//	@RequestMapping(value="withdraw",method = RequestMethod.POST)
	//public String withdraw(HttpServletRequest request,Model model) {}
	
	
	@RequestMapping(value="makeAccount", method=RequestMethod.GET)
	public String makeAccount() {
		return "makeaccount";
	}
	
	//parameter방식 5 -객체로 받기 (대신, form의 name과 dto객체의 속성명과 동일해야함)
	@RequestMapping(value="makeAccount", method=RequestMethod.POST)
	public ModelAndView makeAccount(Account acc) {
		ModelAndView mav = new ModelAndView();
		try {
			Account sacc = accountService.makeAccount(acc);
			mav.addObject("acc",sacc);
			mav.setViewName("accountinfo");
		} catch (Exception e) {
			mav.addObject("err",e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	@RequestMapping(value="allAccountInfo")// default가 GET방식 생략 가능
	public ModelAndView allAccountInfo() {
		ModelAndView mav = new ModelAndView();
		try {
			List<Account> accs = accountService.allAccountInfo();
			mav.addObject("accs",accs);
			mav.setViewName("allaccountinfo");
		} catch (Exception e) {
			mav.addObject("err",e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	@RequestMapping(value="transfer",method= RequestMethod.GET)
	public String transfer() {	
		return "transfer";
	}
	@RequestMapping(value="transfer",method= RequestMethod.POST)
	public ModelAndView transfer(@RequestParam("sid")String sid,@RequestParam("rid")String rid,
			@RequestParam("money")Integer money) {	
		
		ModelAndView  mav = new ModelAndView();
		try {
			accountService.transfer(sid, rid, money);
			Account acc = accountService.accountInfo(sid);
			mav.addObject("acc",acc);
			mav.setViewName("accountinfo");
			
		} catch (Exception e) {
			mav.addObject("err",e.getMessage());
			mav.setViewName("err");
		}
		
		return mav;
	}
	
	//ajax 의 중복체크 처리 위한 mapping (Model을 주지 않음)
	//@ResponseBody  : view가 아니라 데이터를 준다는 의미 
	@RequestMapping(value="accDoubleId",method=RequestMethod.POST)
	@ResponseBody
	public String accDoubleId(@RequestParam("id")String id) {

		try {
			boolean check  = accountService.checkAccDoubleId(id);
			return String.valueOf(check);
				
		} catch (Exception e) {
			e.printStackTrace(); 
			return e.getMessage();
		}

	}
	

}