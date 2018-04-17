package org.spring.code.controllers.admin.member;

import javax.servlet.http.HttpServletRequest;

import org.spring.code.controllers.admin.AdminFrontController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminMemberController extends AdminFrontController {

	
	
	@RequestMapping(value = {"/member/list"}, method = RequestMethod.GET) 
	public String list(ModelMap modelMap,  final HttpServletRequest req ){
		String forward = "admin/member/list.tiles";	
		
		return forward;
		
	}
	
	// 회원가입
	@RequestMapping(value = {"/member/signup"}, method = RequestMethod.GET) 
	public String signup(ModelMap modelMap,  final HttpServletRequest req ){
		String forward = "admin/member/signup.tiles";	
		
		return forward;
		
	}
	
	// 회원가입 정보 변경
	@RequestMapping(value = {"/member/update"}, method = RequestMethod.POST) 
	public String update(ModelMap modelMap,  final HttpServletRequest req ){
		String forward = "admin/member/signup.tiles";	
			
		return forward;			
	}

	
	// 탈퇴
	@RequestMapping(value = {"/member/withdraw"}, method = RequestMethod.GET) 
	public String withdraw(ModelMap modelMap,  final HttpServletRequest req ){
		String forward = "admin/member/withdraw.tiles";	
		
		return forward;
	}
}
