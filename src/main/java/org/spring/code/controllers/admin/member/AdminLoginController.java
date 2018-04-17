package org.spring.code.controllers.admin.member;

import javax.servlet.http.HttpServletRequest;

import org.spring.code.controllers.admin.AdminFrontController;
import org.spring.code.helper.LoggerHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminLoginController extends AdminFrontController {

	@RequestMapping(value = {"/member/login"}, method = RequestMethod.GET) 
	public String loginView(ModelMap modelMap,  final HttpServletRequest req ){
		
		String forward = "admin/member/login.tiles";	
		
		return forward;
	}
	
	
	@RequestMapping(value = {"/member/login"}, method = RequestMethod.POST) 
	public String login(ModelMap modelMap,  final HttpServletRequest req ){
		
		String forward = "/member/login.tiles";	
		
		return forward;
	}
	
	
	@RequestMapping(value = {"/member/logout"}, method = RequestMethod.GET) 
	public String logout(ModelMap modelMap,  final HttpServletRequest req ){
		LoggerHelper.debug(this,"I'm " + this.getClass() + " request : "+ req.getRequestURI());

		String forward = "admin/member/logout.tiles";	
		
		return forward;
		
	}
	
}
