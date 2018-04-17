package org.spring.code.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.code.vo.dao.CodeDao;
import org.spring.code.vo.dto.CodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CodeService{
	
	@Autowired @Qualifier("codeDaoImpl")
	private CodeDao codeDao;	
	
	public List<CodeDto> getCodeDtoList(Map<?,?> params ) {
		return codeDao.select(params);
	}
	
	public Map<String,CodeDto> getCodeDtoMap(Map<?,?> params) {

		Map<String,CodeDto> codeMap = new HashMap<>();
		for(CodeDto vo : getCodeDtoList(params)) {
			codeMap.put(vo.getCode(), vo);
		}
		return codeMap;
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Map<String,Object> saveCodeDto(List<CodeDto> insertCodeDto, List<CodeDto> updateCodeDto, List<CodeDto> deleteCodeDto ){
		// insert, update, delete batch로 한번에 처리 
	
		Map<String,Object> resultBundle = new HashMap<>();
		
		if(insertCodeDto != null && insertCodeDto.size() != 0) {
			//collectionBundle.put("dtoList", insertCodeDto);
			resultBundle.put("insert",insertCodeDto(insertCodeDto));
		}	
		
		// error 발생
		//updateCodeDto.add((T) new CodeDto());
		
		if(updateCodeDto != null && updateCodeDto.size() != 0) {
			//collectionBundle.put("dtoList", updateCodeDto);
			
			List<Object> resultList = new ArrayList<>();
			
			for(CodeDto vo : updateCodeDto){
				resultList.add(updateCodeDto(vo));
			}
			resultBundle.put("update",resultList);
		}
		
		if(deleteCodeDto != null && deleteCodeDto.size() != 0) {
			//collectionBundle.put("dtoList", deleteCodeDto);
			resultBundle.put("delete",deleteCodeDto(deleteCodeDto));
		}
		
		return resultBundle;
	}
	
	private Object insertCodeDto(List<CodeDto> vo){
		return codeDao.insert(vo);
	}
	
	private Object updateCodeDto(CodeDto vo){
		
		return codeDao.update(vo);
	}
	
	private Object deleteCodeDto(List<CodeDto> vo){
		return codeDao.delete(vo);
	}
	
}
