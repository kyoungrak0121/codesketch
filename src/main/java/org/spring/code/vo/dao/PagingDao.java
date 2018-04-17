package org.spring.code.vo.dao;

public interface PagingDao {
	public final static String NAMESPACE = GenericDao.NAMESPACE + "PagingDao."; 
	
	public int getRecordCount();
}
