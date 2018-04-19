package org.spring.code.services;

import org.spring.code.vo.dto.PagingDto;
import org.springframework.stereotype.Service;

@Service
public class PagingService {
	
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
	
	public void setPagingTotalPage(int totalPage){
		pagingDto.setRecordCount(totalPage);
		pagingDto.calculateTotalPage();
	}
	
	public PagingDto getPagingDto(){
		return pagingDto;
	}

}
