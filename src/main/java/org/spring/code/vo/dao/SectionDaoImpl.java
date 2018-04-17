package org.spring.code.vo.dao;

import java.util.List;
import java.util.Map;

import org.spring.code.vo.dto.SectionDto;
import org.springframework.stereotype.Repository;

@Repository
public class SectionDaoImpl extends GenericDaoImpl<SectionDto> implements SectionDao{
	
	@Override
	public List<SectionDto> select(Map<?,?> params)  { 
		// TODO Auto-generated method stub
		String queryId = SectionDao.NAMESPACE+"select";
		
		return super.select(queryId, params);
	}
	
	@Override
	public Object insert(List<SectionDto> vo){
		// TODO Auto-generated method stub
		String queryId = SectionDao.NAMESPACE+"insert";
		
		return super.insert(queryId,vo);
	}
	
	@Override
	public Object update(SectionDto vo){
		// TODO Auto-generated method stub
		String queryId = SectionDao.NAMESPACE+"update";
	
		return super.update(queryId,vo);
	}
	
	@Override
	public Object delete(List<SectionDto> vo){
		// TODO Auto-generated method stub
		String queryId = SectionDao.NAMESPACE+"delete";
		
		return super.delete(queryId,vo);
	}
	
}
