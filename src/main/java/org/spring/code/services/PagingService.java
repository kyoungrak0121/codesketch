package org.spring.code.services;

import org.spring.code.vo.dao.PagingDao;
import org.spring.code.vo.dto.PagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PagingService {
	
	@Autowired @Qualifier("pagingDaompl")
	private PagingDao pagingDao ; 
	
	// DTO
	private PagingDto pagingDto ; 
	
	public PagingService(){
		pagingDto = new PagingDto();
	}
	
	public void setPagingIndex(int page, int pageSize) {
		pagingDto.setPage(page);
		pagingDto.setPageSize(pageSize);
		pagingDto.calculatePagingIndex();
    }
	
	public void setPagingTotalPage() {
		pagingDto.setRecordCount(pagingDao.getRecordCount());
		pagingDto.calculateTotalPage();
	}
	
	public PagingDto getPagingDto(){
		return pagingDto;
	}

}
