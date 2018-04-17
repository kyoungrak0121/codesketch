package org.spring.code.vo.dao;

import org.spring.code.vo.dto.PagingDto;
import org.springframework.stereotype.Repository;

@Repository
public class PagingDaompl extends GenericDaoImpl<PagingDto> implements PagingDao {
	
	@Override
	public int getRecordCount() {
		String queryId = PagingDao.NAMESPACE+"recordCount";
		return sqlSession.selectOne(queryId);
	}

}
