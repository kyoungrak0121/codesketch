package org.spring.code.controllers.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.spring.code.controllers.CommonController;
import org.spring.code.services.MenuService;
import org.spring.code.services.ViewService;
import org.spring.code.vo.dto.MenuDto;
import org.spring.code.vo.dto.ViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class UserFrontController extends CommonController{
	
	@Autowired @Qualifier("menuService")
	private MenuService menuService;
	
	@Autowired @Qualifier("viewService")
	private ViewService viewService;
	
	protected static List<MenuDto> menuDtoList ;
	protected static List<ViewDto> viewDtoList ;
	protected static Map<String,ViewDto> viewDtoMap; 
	
	/* @Autowired필드가 설정 되기 전 생성자가 호출됨에 따라 초기화는 PostContruct Annotation을 사용해서 함*/	
	@PostConstruct
	public void init() {
		menuDtoList = menuService.getMenuDto(new HashMap<>());
		viewDtoList = viewService.getViewDto(new HashMap<>());
		viewDtoMap = viewParser() ;
	}
	

	@ModelAttribute
    public void addFrontAttributes(ModelMap modelMap, final HttpServletRequest req) {

		// menu binding
		modelMap.addAttribute("menuDtoList", menuDtoList);
		// view binding
		modelMap.addAttribute("viewDto", viewDtoMap.get(req.getRequestURI()));
    }
	
	
	// viewList 변환 -> Map Collection(RequestUri,FilePath);
	private Map<String,ViewDto> viewParser(){
		// view parsing
		Map<String,ViewDto> viewMap = new HashMap<>(); 
		for(ViewDto view : viewDtoList) {
			viewMap.put(view.getRequestUri(),view);
		}
		return viewMap;
	}
	
}
