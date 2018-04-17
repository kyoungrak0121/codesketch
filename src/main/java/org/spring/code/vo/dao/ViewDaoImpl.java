package org.spring.code.vo.dao;

import java.util.List;
import java.util.Map;

import org.spring.code.vo.dto.ViewDto;
import org.springframework.stereotype.Repository;

@Repository
public class ViewDaoImpl extends GenericDaoImpl<ViewDto> implements ViewDao{
	
	@Override
	public List<ViewDto> select(Map<?,?> params)  { 
		// TODO Auto-generated method stub
		String queryId = ViewDao.NAMESPACE+"select";
		
		return super.select(queryId, params);
	}
	
	@Override
	public Object insert(List<ViewDto> vo){
		// TODO Auto-generated method stub
		String queryId = ViewDao.NAMESPACE+"insert";
		
		return super.insert(queryId,vo);
	}
	
	@Override
	public Object update(ViewDto vo){
		// TODO Auto-generated method stub
		String queryId = ViewDao.NAMESPACE+"update";
	
		return super.update(queryId,vo);
	}
	
	@Override
	public Object delete(List<ViewDto> vo){
		// TODO Auto-generated method stub
		String queryId = ViewDao.NAMESPACE+"delete";
		
		return super.delete(queryId,vo);
	}
	
}
