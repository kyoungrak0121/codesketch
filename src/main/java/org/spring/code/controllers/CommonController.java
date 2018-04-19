package org.spring.code.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.spring.code.helper.JsonHelper;
import org.spring.code.services.CodeService;
import org.spring.code.vo.dto.CodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Admin, Client 공통 Controller  
 * 공통 properties 설정 등
 */
@Controller
public class CommonController {

/* common service load */
	
	// configuration 
	
	@Autowired @Qualifier("codeService")
	private CodeService codeService;

	
	/* common object declaration */	
	// user, admin 에서 공통으로 쓰는 static 변수. 데이터 변경 시 다시 대입 해줘야한다. 
	protected static Map<String,CodeDto> codeDtoMap ;


	
	/* @Autowired필드가 설정 되기 전 생성자가 호출됨에 따라 초기화는 PostContruct Annotation을 사용해서 함*/
	@PostConstruct
	protected void init(){
		codeDtoMap = codeService.getCodeDtoMap(new HashMap<>());
	
	}
	
	//Auto add common attribute to modelMap 

	@ModelAttribute
    public void addAttributes(ModelMap modelMap) {
		// 나중에 db config data 읽어서 setting 
		modelMap.addAttribute("cache", System.currentTimeMillis());
		
		// common code 
		modelMap.addAttribute("code", JsonHelper.toJson(codeDtoMap));
    }

	/* params parser helper Method */
	protected Map<String, Object> paserRequetToHashMap(HttpServletRequest req){
		
		Map<String,Object> params = new HashMap<>();
		
		Enumeration<?> enumber = req.getParameterNames();

		while (enumber.hasMoreElements()) {
			String key = enumber.nextElement().toString();
			Object value = req.getParameter(key);
			params.put(key, value);  
		}
		
		
		return params;
	}
	
	

}
