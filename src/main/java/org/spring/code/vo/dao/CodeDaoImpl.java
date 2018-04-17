package org.spring.code.vo.dao;

import java.util.List;
import java.util.Map;

import org.spring.code.vo.dto.CodeDto;
import org.springframework.stereotype.Repository;

@Repository
public class CodeDaoImpl extends GenericDaoImpl<CodeDto> implements CodeDao {
	
	@Override
	public List<CodeDto> select(Map<?,?> params){ 
		// TODO Auto-generated method stub
		String queryId = CodeDao.NAMESPACE+"select";
		
		return super.select(queryId, params);
	}
	
	@Override
	public Object insert(List<CodeDto> vo){
		// TODO Auto-generated method stub
		String queryId = CodeDao.NAMESPACE+"insert";
		
		return super.insert(queryId,vo);
	}
	
	@Override
	public Object update(CodeDto vo){
		// TODO Auto-generated method stub
		String queryId = CodeDao.NAMESPACE+"update";
	
		return super.update(queryId,vo);
	}
	
	@Override
	public Object delete(List<CodeDto> vo){
		// TODO Auto-generated method stub
		String queryId = CodeDao.NAMESPACE+"delete";
		
		return super.delete(queryId,vo);
	}
	
}
