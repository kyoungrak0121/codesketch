package org.spring.code.services;

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
		return codeDao.selectList(params);
	}
	
	public Map<String,CodeDto> getCodeDtoMap(Map<?,?> params) {

		Map<String,CodeDto> codeMap = new HashMap<>();
		for(CodeDto vo : getCodeDtoList(params)) {
			codeMap.put(vo.getCode(), vo);
		}
		return codeMap;
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object insertCodeDto(List<CodeDto> vo){
		return codeDao.insert(vo);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object updateCodeDto(CodeDto vo){
		
		return codeDao.update(vo);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object deleteCodeDto(List<CodeDto> vo){
		return codeDao.delete(vo);
	}
	
}
