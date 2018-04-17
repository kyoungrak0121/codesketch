package org.spring.code.controllers.admin.main;

import java.util.Locale;

import org.spring.code.controllers.admin.AdminFrontController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminMainController extends AdminFrontController {
	
	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		String forward = "admin/main/index.tiles";
		
		//model.addAttribute("serverTime", System.currentTimeMillis() );
	
		return forward;
	}	
}
