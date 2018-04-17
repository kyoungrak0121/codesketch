package org.spring.code.services;

import java.util.ArrayList;
import java.util.HashMap;
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
		return menuDao.select(params);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public Map<String,Object> saveMenuDto(List<MenuDto> insertMenuDto, List<MenuDto> updateMenuDto, List<MenuDto> deleteMenuDto ){
		// insert, update, delete batch로 한번에 처리 
	
		Map<String,Object> resultBundle = new HashMap<>();
		
		if(insertMenuDto != null && insertMenuDto.size() != 0) {
			//collectionBundle.put("dtoList", insertMenuDto);
			resultBundle.put("insert",insertMenuDto(insertMenuDto));
		}	
		
		// error 발생
		//updateMenuDto.add((T) new MenuDto());
		
		if(updateMenuDto != null && updateMenuDto.size() != 0) {
			//collectionBundle.put("dtoList", updateMenuDto);
			
			List<Object> resultList = new ArrayList<>();
			
			for(MenuDto vo : updateMenuDto){
				resultList.add(updateMenuDto(vo));
			}
			resultBundle.put("update",resultList);
		}
		
		if(deleteMenuDto != null && deleteMenuDto.size() != 0) {
			//collectionBundle.put("dtoList", deleteMenuDto);
			resultBundle.put("delete",deleteMenuDto(deleteMenuDto));
		}
		
		return resultBundle;
	}
	
	private Object insertMenuDto(List<MenuDto> vo){
		return menuDao.insert(vo);
	}
	
	private Object updateMenuDto(MenuDto vo){
		
		return menuDao.update(vo);
	}
	
	private Object deleteMenuDto(List<MenuDto> vo){
		return menuDao.delete(vo);
	}

}
