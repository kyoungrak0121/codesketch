package org.spring.code.controllers.admin.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.spring.code.controllers.admin.AdminFrontController;
import org.spring.code.helper.JsonHelper;
import org.spring.code.helper.LoggerHelper;
import org.spring.code.helper.ResponseHelper;
import org.spring.code.services.CodeService;
import org.spring.code.vo.dto.CodeDto;
import org.spring.code.vo.dto.JqgridDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminCodeController extends AdminFrontController {
	
	@Autowired @Qualifier("codeService")
	private CodeService codeService;
	
	
	@RequestMapping(value = {"/setting/code"}, method = RequestMethod.GET) 
	public String codeView(ModelMap modelMap,  final HttpServletRequest req ){
		String forward = "admin/setting/code.tiles";
		
		return forward;
	}	
	
	
	@RequestMapping(value = {"/setting/code/load"}, method = {RequestMethod.GET}, produces = "application/json; charset=utf8")
	public ResponseEntity<Object> loadCodeDtoList(ModelMap modelMap , final HttpServletRequest req ){
		
		//first step. Requet => HashMap convert
		Map<String,Object> params = paserRequetToHashMap(req);	
 		
 		JqgridDto jqgridDto = new JqgridDto(); 
 				
 		jqgridDto.setData(codeService.getCodeDtoList(params));
 		
 		pagingService.setPagingTotalPage();
 	
 		jqgridDto.setPagingDto(pagingService.getPagingDto());
 	
		return ResponseHelper.send(JsonHelper.toJson(jqgridDto), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = {"/setting/code/save"}, method = {RequestMethod.POST})
	public ResponseEntity<Object> saveCode(ModelMap modelMap , final HttpServletRequest req ){
		LoggerHelper.debug(this,"I'm " + this.getClass() + " request : "+ req.getRequestURI());
		
 		Map<String,Object> params = paserRequetToHashMap(req);	
 		
 		String jsonData = params.get("data").toString();

 		List<CodeDto> insertDto = JsonHelper.getJsonToObjectList(jsonData, "insert", CodeDto.class);
 		List<CodeDto> updateDto = JsonHelper.getJsonToObjectList(jsonData, "update", CodeDto.class);
 		List<CodeDto> deleteDto = JsonHelper.getJsonToObjectList(jsonData, "delete", CodeDto.class);
 		
 		codeService.saveCodeDto( insertDto, updateDto, deleteDto );
 		
 		codeDtoMap = codeService.getCodeDtoMap(new HashMap<>());

 		return ResponseHelper.send(codeDtoMap.get("E000200"), HttpStatus.OK);
	}
	
}
