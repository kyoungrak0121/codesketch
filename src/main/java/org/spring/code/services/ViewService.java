package org.spring.code.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.code.vo.dao.ViewDao;
import org.spring.code.vo.dto.ViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ViewService{

	@Autowired @Qualifier("viewDaoImpl")
	private ViewDao viewDao;	
	
	public List<ViewDto> getViewDto(Map<?,?> params){
		return viewDao.select(params);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Map<String,Object> saveViewDto(List<ViewDto> insertDto, List<ViewDto> updateDto, List<ViewDto> deleteDto ){
		// insert, update, delete batch로 한번에 처리 
	
		Map<String,Object> resultBundle = new HashMap<>();
		
		if(insertDto != null && insertDto.size() != 0) {
			//collectionBundle.put("dtoList", insertViewDto);
			resultBundle.put("insert",insertViewDto(insertDto));
		}	
		
		// error 발생
		//updateViewDto.add((T) new ViewDto());
		
		if(updateDto != null && updateDto.size() != 0) {
			//collectionBundle.put("dtoList", updateViewDto);
			
			List<Object> resultList = new ArrayList<>();
			
			for(ViewDto vo : updateDto){
				resultList.add(updateViewDto(vo));
			}
			resultBundle.put("update",resultList);
		}
		
		if(deleteDto != null && deleteDto.size() != 0) {
			//collectionBundle.put("dtoList", deleteViewDto);
			resultBundle.put("delete",deleteViewDto(deleteDto));
		}
		
		return resultBundle;
	}
	
	private Object insertViewDto(List<ViewDto> vo){
		return viewDao.insert(vo);
	}
	
	private Object updateViewDto(ViewDto vo){
		
		return viewDao.update(vo);
	}
	
	private Object deleteViewDto(List<ViewDto> vo){
		return viewDao.delete(vo);
	}
}
