package org.spring.code.controllers.users.main;

import javax.servlet.http.HttpServletRequest;

import org.spring.code.controllers.users.UserFrontController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserMainController extends UserFrontController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap modelMap , final HttpServletRequest req ){
		init();
		
		String forward = viewDtoMap.get(req.getRequestURI()).getFilePath();
		
		return forward;
	}
}
