package org.spring.code.vo.dao;

import java.util.List;
import java.util.Map;

import org.spring.code.vo.dto.SectionDto;
import org.springframework.stereotype.Repository;

@Repository
public class ViewRelationDaoImpl extends CommonDaoImpl<SectionDto> implements ViewRelationDao{
	
	@Override
	public List<SectionDto> selectList(Map<?,?> params)  { 
		// TODO Auto-generated method stub
		String queryId = ViewRelationDao.NAMESPACE+"select";
		
		return super.selectList(queryId, params);
	}
	
	@Override
	public Object insert(List<SectionDto> vo){
		// TODO Auto-generated method stub
		String queryId = ViewRelationDao.NAMESPACE+"insert";
		
		return super.insert(queryId,vo);
	}
	
	@Override
	public Object update(SectionDto vo){
		// TODO Auto-generated method stub
		String queryId = ViewRelationDao.NAMESPACE+"update";
	
		return super.update(queryId,vo);
	}
	
	@Override
	public Object delete(List<SectionDto> vo){
		// TODO Auto-generated method stub
		String queryId = ViewRelationDao.NAMESPACE+"delete";
		
		return super.delete(queryId,vo);
	}
	
}
