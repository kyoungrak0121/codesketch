package org.spring.code.controllers.admin.design;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.spring.code.controllers.admin.AdminFrontController;
import org.spring.code.helper.JsonHelper;
import org.spring.code.helper.LoggerHelper;
import org.spring.code.helper.ResponseHelper;
import org.spring.code.services.ViewRelationService;
import org.spring.code.services.ViewService;
import org.spring.code.vo.dto.JqgridDto;
import org.spring.code.vo.dto.SectionDto;
import org.spring.code.vo.dto.ViewDto;
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
public class AdminViewController extends AdminFrontController {
	
	@Autowired @Qualifier("viewService")
	private ViewService viewService ;
	
	@Autowired @Qualifier("viewRelationService")
	private ViewRelationService viewRelationService ;
	
	
	@RequestMapping(value = {"/design/view"}, method = RequestMethod.GET) 
	public String viewView(ModelMap modelMap,  final HttpServletRequest req ){
		String forward = "admin/design/view.tiles";	
		
		return forward;
	}	
	
	@RequestMapping(value = {"/design/view/load"}, method = {RequestMethod.GET}, produces = "application/json; charset=utf8")
	public ResponseEntity<Object> loadViewDtoList(ModelMap modelMap , final HttpServletRequest req ){
		Map<String,Object> params = paserRequetToHashMap(req);	
		
 		JqgridDto jqgridDto = new JqgridDto(); 
 		List<ViewDto> viewList = viewService.getViewDto(params) ; 	
 		
 		jqgridDto.setData(viewList);
 		
 		pagingService.setPagingTotalPage(viewList.size());
 	
 		jqgridDto.setPagingDto(pagingService.getPagingDto());
 		
		return ResponseHelper.send(JsonHelper.toJson(jqgridDto), HttpStatus.OK);
	}	
	
	@RequestMapping(value = {"/design/view/save"}, method = {RequestMethod.POST})
	public ResponseEntity<Object> saveView(ModelMap modelMap , final HttpServletRequest req ){
		
		// Requet => HashMap convert => result map = params
		Map<String,Object> params = paserRequetToHashMap(req);	
 		
 		//if(params.get("data") == null) { return "false" ;}
 		String jsonData = params.get("data").toString();

 		List<ViewDto> insertViewDto = JsonHelper.getJsonToObjectList(jsonData, "insert", ViewDto.class);
 		List<ViewDto> updateViewDto = JsonHelper.getJsonToObjectList(jsonData, "update", ViewDto.class);
 		List<ViewDto> deleteViewDto = JsonHelper.getJsonToObjectList(jsonData, "delete", ViewDto.class);
 		
 		saveViewDto( insertViewDto, updateViewDto, deleteViewDto );

 		return ResponseHelper.send(codeDtoMap.get("E000200"), HttpStatus.OK);
	}
	
	@Transactional (rollbackFor = Exception.class)
	private void saveViewDto(List<ViewDto> insertDto, List<ViewDto> updateDto, List<ViewDto> deleteDto ){
		// insert, update, delete batch로 한번에 처리 
	
		if(insertDto != null && insertDto.size() != 0) {
			viewService.insertViewDto(insertDto);
		}
		
		if(updateDto != null && updateDto.size() != 0) {
			for(ViewDto vo : updateDto){
				viewService.updateViewDto(vo);
			}
		}
		
		if(deleteDto != null && deleteDto.size() != 0) {
			viewService.deleteViewDto(deleteDto);
		}
		
	}
	
	/* view section relation */
	@RequestMapping(value = {"/design/view/relation"}, method = {RequestMethod.GET})
	public String relationViewAndSection(ModelMap modelMap , final HttpServletRequest req ){
		String forward = "admin/design/view/relation";	
		
		return forward;
	}

	/* view section related save */
	@RequestMapping(value= {"/design/view/relation/save"}, method = {RequestMethod.POST})
	public ResponseEntity<Object> saveRelatedSection(ModelMap modelMap , final HttpServletRequest req){
	
		// Requet => HashMap convert => result map = params
		Map<String,Object> params = paserRequetToHashMap(req);	
		
		String viewJson = params.get("view").toString();
		String sectionJson =  params.get("section").toString();
		
		ViewDto view = (ViewDto)JsonHelper.getJsonToObjectList(viewJson,null,ViewDto.class).get(0);
		
		Map<String,List<SectionDto>> mapDto = new HashMap<String,List<SectionDto>>();
		
		mapDto.put("insert", JsonHelper.getJsonToObjectList(sectionJson, "insert", SectionDto.class)) ;
		mapDto.put("update", JsonHelper.getJsonToObjectList(sectionJson, "update", SectionDto.class)) ;
		mapDto.put("delete", JsonHelper.getJsonToObjectList(sectionJson, "delete", SectionDto.class)) ;
		 		
 		saveRelationDto(view ,mapDto);
		
		
		return ResponseHelper.send(codeDtoMap.get("E000200"), HttpStatus.OK);
	}
	
	@Transactional (rollbackFor = Exception.class)
	private void saveRelationDto(ViewDto view ,Map<String,List<SectionDto>> mapDto) {
		// TODO Auto-generated method stub
		
		// it is setting the view_seq , before section be inserted
		for(String k : mapDto.keySet()) {
			if(mapDto.get(k) != null) {
				for( final SectionDto section : mapDto.get(k)) {
					section.setViewSeq(view.getViewSeq());
				}
			}
		}
		
		LoggerHelper.debug(this,mapDto);
		
		List<SectionDto> insertDto = mapDto.get("insert");
		List<SectionDto> updateDto = mapDto.get("update");
		List<SectionDto> deleteDto = mapDto.get("delete");
					
		
		if(insertDto != null && insertDto.size() != 0) {
			viewRelationService.insertRelation(insertDto);
		}
		
		if(updateDto != null && updateDto.size() != 0) {
			for(SectionDto vo : updateDto){
				viewRelationService.updateRelation(vo);
			}
		}
		
		if(deleteDto != null && deleteDto.size() != 0) {
			viewRelationService.deleteRelation(deleteDto);
		}
		
	}
	
	
	
	
}
