package org.spring.code.vo.dao;

import java.util.List;
import java.util.Map;

import org.spring.code.vo.dto.MenuDto;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDaoImpl extends GenericDaoImpl<MenuDto> implements MenuDao{
	
	@Override
	public List<MenuDto> select(Map<?,?> params)  { 
		// TODO Auto-generated method stub
		String queryId = MenuDao.NAMESPACE+"select";
		
		return super.select(queryId, params);
	}
	
	@Override
	public Object insert(List<MenuDto> vo){
		// TODO Auto-generated method stub
		String queryId = MenuDao.NAMESPACE+"insert";
		
		return super.insert(queryId,vo);
	}
	
	@Override
	public Object update(MenuDto vo){
		// TODO Auto-generated method stub
		String queryId = MenuDao.NAMESPACE+"update";
	
		return super.update(queryId,vo);
	}
	
	@Override
	public Object delete(List<MenuDto> vo){
		// TODO Auto-generated method stub
		String queryId = MenuDao.NAMESPACE+"delete";
		
		return super.delete(queryId,vo);
	}
	
}
