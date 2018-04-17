package org.spring.code.controllers.admin.design;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.spring.code.controllers.admin.AdminFrontController;
import org.spring.code.helper.JsonHelper;
import org.spring.code.helper.ResponseHelper;
import org.spring.code.services.ViewService;
import org.spring.code.vo.dto.JqgridDto;
import org.spring.code.vo.dto.ViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminViewController extends AdminFrontController {
	
	@Autowired @Qualifier("viewService")
	private ViewService viewService ;
	
	@RequestMapping(value = {"/design/view"}, method = RequestMethod.GET) 
	public String viewView(ModelMap modelMap,  final HttpServletRequest req ){
		String forward = "admin/design/view.tiles";	
		
		return forward;
	}	
	
	@RequestMapping(value = {"/design/view/load"}, method = {RequestMethod.GET}, produces = "application/json; charset=utf8")
	public ResponseEntity<Object> loadViewDtoList(ModelMap modelMap , final HttpServletRequest req ){
		Map<String,Object> params = paserRequetToHashMap(req);	
		
 		JqgridDto jqgridDto = new JqgridDto(); 
 				
 		jqgridDto.setData(viewService.getViewDto(params));
 		
 		pagingService.setPagingTotalPage();
 	
 		jqgridDto.setPagingDto(pagingService.getPagingDto());
 		
		return ResponseHelper.send(JsonHelper.toJson(jqgridDto), HttpStatus.OK);
	}	
	
	@RequestMapping(value = {"/design/view/save"}, method = {RequestMethod.POST})
	public ResponseEntity<Object> saveView(ModelMap modelMap , final HttpServletRequest req ){
		
		// Requet => HashMap convert => result map = params
		Map<String,Object> params = paserRequetToHashMap(req);	
 		
 		//if(params.get("data") == null) { return "false" ;}
 		String jsonData = params.get("data").toString();

 		List<ViewDto> insertDto = JsonHelper.getJsonToObjectList(jsonData, "insert", ViewDto.class);
 		List<ViewDto> updateDto = JsonHelper.getJsonToObjectList(jsonData, "update", ViewDto.class);
 		List<ViewDto> deleteDto = JsonHelper.getJsonToObjectList(jsonData, "delete", ViewDto.class);
 		
 		viewService.saveViewDto( insertDto, updateDto, deleteDto );

 		return ResponseHelper.send(codeDtoMap.get("E000200"), HttpStatus.OK);
	}
	
	/* view section relation */
	@RequestMapping(value = {"/design/view/relation"}, method = {RequestMethod.GET})
	public String relationViewAndSection(ModelMap modelMap , final HttpServletRequest req ){
		String forward = "admin/design/view/relation";	
		
		return forward;
	}
	
}
