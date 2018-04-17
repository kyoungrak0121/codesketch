package org.spring.code.vo.dao;

import org.spring.code.vo.dto.CodeDto;

public interface CodeDao extends GenericDao<CodeDto> {
	public final static String NAMESPACE = GenericDao.NAMESPACE + "CodeDao.";
	
}
