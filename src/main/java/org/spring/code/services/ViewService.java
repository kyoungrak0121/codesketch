package org.spring.code.services;

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
		return viewDao.selectList(params);
	}
	

	
	@Transactional (rollbackFor = Exception.class)
	public Object insertViewDto(List<ViewDto> vo){
		return viewDao.insert(vo);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object updateViewDto(ViewDto vo){
		
		return viewDao.update(vo);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object deleteViewDto(List<ViewDto> vo){
		return viewDao.delete(vo);
	}
	
	
	
	
	
	

}
