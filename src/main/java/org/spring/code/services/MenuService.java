package org.spring.code.services;

import java.util.List;
import java.util.Map;

import org.spring.code.vo.dao.MenuDao;
import org.spring.code.vo.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MenuService{

	@Autowired @Qualifier("menuDaoImpl")
	private MenuDao menuDao;	
	
	public List<MenuDto> getMenuDto(Map<?,?> params){
		return menuDao.selectList(params);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object insertMenuDto(List<MenuDto> vo){
		return menuDao.insert(vo);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object updateMenuDto(MenuDto vo){
		
		return menuDao.update(vo);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Object deleteMenuDto(List<MenuDto> vo){
		return menuDao.delete(vo);
	}

}
