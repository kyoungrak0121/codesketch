package org.spring.code.controllers.admin.design;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.spring.code.controllers.admin.AdminFrontController;
import org.spring.code.helper.JsonHelper;
import org.spring.code.helper.ResponseHelper;
import org.spring.code.services.SectionService;
import org.spring.code.vo.dto.JqgridDto;
import org.spring.code.vo.dto.SectionDto;
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
public class AdminSectionController extends AdminFrontController {
	
	@Autowired @Qualifier("sectionService")
	private SectionService sectionService ;
	
	@RequestMapping(value = {"/design/section"}, method = RequestMethod.GET) 
	public String viewSection(ModelMap modelMap,  final HttpServletRequest req ){
		String forward = "admin/design/section.tiles";	
		
		return forward;
	}	
	
	@RequestMapping(value = {"/design/section/load"}, method = {RequestMethod.GET}, produces = "application/json; charset=utf8")
	public ResponseEntity<Object> loadSectionDtoList(ModelMap modelMap , final HttpServletRequest req ){
		Map<String,Object> params = paserRequetToHashMap(req);	
		
 		JqgridDto jqgridDto = new JqgridDto(); 
 		List<SectionDto> sectionList = sectionService.getSectionDto(params) ; 
 		
 		jqgridDto.setData(sectionList);
 		
 		pagingService.setPagingTotalPage(sectionList.size());
 	
 		jqgridDto.setPagingDto(pagingService.getPagingDto());
 		
		return ResponseHelper.send(JsonHelper.toJson(jqgridDto), HttpStatus.OK);
	}	
	
	@RequestMapping(value = {"/design/section/save"}, method = {RequestMethod.POST})
	public ResponseEntity<Object> saveSection(ModelMap modelMap , final HttpServletRequest req ){
		
		// Requet => HashMap convert => result map = params
		Map<String,Object> params = paserRequetToHashMap(req);	
 		
 		//if(params.get("data") == null) { return "false" ;}
 		String jsonData = params.get("data").toString();

 		List<SectionDto> insertDto = JsonHelper.getJsonToObjectList(jsonData, "insert", SectionDto.class);
 		List<SectionDto> updateDto = JsonHelper.getJsonToObjectList(jsonData, "update", SectionDto.class);
 		List<SectionDto> deleteDto = JsonHelper.getJsonToObjectList(jsonData, "delete", SectionDto.class);
 		
 		saveSectionDto( insertDto, updateDto, deleteDto );

 		return ResponseHelper.send(codeDtoMap.get("E000200"), HttpStatus.OK);
	}
	
	
	@Transactional (rollbackFor = Exception.class)
	public void saveSectionDto(List<SectionDto> insertDto, List<SectionDto> updateDto, List<SectionDto> deleteDto ){
		// insert, update, delete batch로 한번에 처리 
	
		if(insertDto != null && insertDto.size() != 0) {
			sectionService.insertSectionDto(insertDto);
		}
		
		if(updateDto != null && updateDto.size() != 0) {
			for(SectionDto vo : updateDto){
				sectionService.updateSectionDto(vo);
			}
		}
		
		if(deleteDto != null && deleteDto.size() != 0) {
			sectionService.deleteSectionDto(deleteDto);
		}
	}
}
