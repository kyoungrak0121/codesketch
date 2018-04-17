package org.spring.code.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.code.vo.dao.SectionDao;
import org.spring.code.vo.dto.SectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SectionService{

	@Autowired @Qualifier("sectionDaoImpl")
	private SectionDao sectionDao;	
	
	public List<SectionDto> getSectionDto(Map<?,?> params){
		return sectionDao.select(params);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Map<String,Object> saveSectionDto(List<SectionDto> insertDto, List<SectionDto> updateDto, List<SectionDto> deleteDto ){
		// insert, update, delete batch로 한번에 처리 
	
		Map<String,Object> resultBundle = new HashMap<>();
		
		if(insertDto != null && insertDto.size() != 0) {
			//collectionBundle.put("dtoList", insertSectionDto);
			resultBundle.put("insert",insertSectionDto(insertDto));
		}	
		
		// error 발생
		//updateSectionDto.add((T) new SectionDto());
		
		if(updateDto != null && updateDto.size() != 0) {
			//collectionBundle.put("dtoList", updateSectionDto);
			
			List<Object> resultList = new ArrayList<>();
			
			for(SectionDto vo : updateDto){
				resultList.add(updateSectionDto(vo));
			}
			resultBundle.put("update",resultList);
		}
		
		if(deleteDto != null && deleteDto.size() != 0) {
			//collectionBundle.put("dtoList", deleteSectionDto);
			resultBundle.put("delete",deleteSectionDto(deleteDto));
		}
		
		return resultBundle;
	}
	
	private Object insertSectionDto(List<SectionDto> vo){
		return sectionDao.insert(vo);
	}
	
	private Object updateSectionDto(SectionDto vo){
		
		return sectionDao.update(vo);
	}
	
	private Object deleteSectionDto(List<SectionDto> vo){
		return sectionDao.delete(vo);
	}
}
