package org.spring.code.controllers.admin.design;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.spring.code.controllers.admin.AdminFrontController;
import org.spring.code.helper.JsonHelper;
import org.spring.code.helper.ResponseHelper;
import org.spring.code.services.MenuService;
import org.spring.code.vo.dto.JqgridDto;
import org.spring.code.vo.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminMenuController extends AdminFrontController {
	
	@Autowired @Qualifier("menuService")
	private MenuService menuService;
	
	@RequestMapping(value = {"/design/menu"}, method = RequestMethod.GET) 
	public String menuView(ModelMap modelMap,  final HttpServletRequest req ){
		String forward = "admin/design/menu.tiles";	
		
		return forward;
	}	
	
	@RequestMapping(value = {"/design/menu/load"}, method = {RequestMethod.GET}, produces = "application/json; charset=utf8")
	public ResponseEntity<Object> loadMenuDtoList(ModelMap modelMap , final HttpServletRequest req ){
		
		//first step. Requet => HashMap convert
		Map<String,Object> params = paserRequetToHashMap(req);	

		// get MenuDtoList in database
 		JqgridDto jqgridDto = new JqgridDto(); 
 		List<MenuDto> menuList = menuService.getMenuDto(params) ; 
 		
 		jqgridDto.setData(menuList);
 		
 		pagingService.setPagingTotalPage(menuList.size());
 	
 		jqgridDto.setPagingDto(pagingService.getPagingDto());

		return ResponseHelper.send(JsonHelper.toJson(jqgridDto), HttpStatus.OK);
	}
	

	@RequestMapping(value = {"/design/menu/save"}, method = {RequestMethod.POST})
	public ResponseEntity<Object> saveMenu(ModelMap modelMap , final HttpServletRequest req ){
		
		// Requet => HashMap convert => result map = params
		Map<String,Object> params = paserRequetToHashMap(req);	
 		
 		//if(params.get("data") == null) { return "false" ;}
 		String jsonData = params.get("data").toString();

 		List<MenuDto> insertDto = JsonHelper.getJsonToObjectList(jsonData, "insert", MenuDto.class);
 		List<MenuDto> updateDto = JsonHelper.getJsonToObjectList(jsonData, "update", MenuDto.class);
 		List<MenuDto> deleteDto = JsonHelper.getJsonToObjectList(jsonData, "delete", MenuDto.class);
 		
 		saveMenuDto( insertDto, updateDto, deleteDto );

 		return ResponseHelper.send(codeDtoMap.get("E000200"), HttpStatus.OK);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public void saveMenuDto(List<MenuDto> insertMenuDto, List<MenuDto> updateMenuDto, List<MenuDto> deleteMenuDto ){
		// insert, update, delete batch로 한번에 처리 
		
		if(insertMenuDto != null && insertMenuDto.size() != 0) {
			//collectionBundle.put("dtoList", insertMenuDto);
			menuService.insertMenuDto(insertMenuDto);
		}	
				
		if(updateMenuDto != null && updateMenuDto.size() != 0) {
			//collectionBundle.put("dtoList", updateMenuDto);
			for(MenuDto vo : updateMenuDto){
				menuService.updateMenuDto(vo);
			}
		}
		
		if(deleteMenuDto != null && deleteMenuDto.size() != 0) {
			//collectionBundle.put("dtoList", deleteMenuDto);
			menuService.deleteMenuDto(deleteMenuDto);
		}
		
	}
	
}
