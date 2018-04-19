package org.spring.code.controllers.admin;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.spring.code.controllers.CommonController;
import org.spring.code.services.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin")
public class AdminFrontController extends CommonController{

	
	@Autowired @Qualifier("pagingService")
	protected PagingService pagingService;
	
	
	@PostConstruct
	protected void init(){
		
	}
	
	/* params parser helper Method */
	@Override
	protected Map<String,Object> paserRequetToHashMap(HttpServletRequest req){
		
		Map<String,Object> params = super.paserRequetToHashMap(req);
		
		if(params.get("page") != null && params.get("pageSize") != null){
		
			int page = Integer.parseInt(params.get("page").toString());
			int pageSize = Integer.parseInt(params.get("pageSize").toString());
			
			pagingService.setPagingIndex(page,pageSize);
			
			params.put("startIndex", pagingService.getPagingDto().getStartIndex());
			params.put("endIndex", pagingService.getPagingDto().getEndIndex());
		}
		
 		return params ;
	}
	
	
}
