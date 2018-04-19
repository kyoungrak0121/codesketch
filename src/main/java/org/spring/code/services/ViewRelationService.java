package org.spring.code.services;

import java.util.List;

import org.spring.code.vo.dao.ViewRelationDao;
import org.spring.code.vo.dto.SectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViewRelationService{

	@Autowired @Qualifier("viewRelationDaoImpl")
	private ViewRelationDao viewRelationDao;	
	

	@Transactional (rollbackFor = Exception.class)
	public Object insertRelation(List<SectionDto> vo){
		return viewRelationDao.insert(vo);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object updateRelation(SectionDto vo){
		return viewRelationDao.update(vo);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object deleteRelation(List<SectionDto> vo){
		return viewRelationDao.delete(vo);
	}
	
	
}
