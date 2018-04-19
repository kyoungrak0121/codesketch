package org.spring.code.services;

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
		return sectionDao.selectList(params);
	}
	
	
	@Transactional (rollbackFor = Exception.class)
	public Object insertSectionDto(List<SectionDto> vo){
		return sectionDao.insert(vo);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object updateSectionDto(SectionDto vo){
		
		return sectionDao.update(vo);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object deleteSectionDto(List<SectionDto> vo){
		return sectionDao.delete(vo);
	}
}
