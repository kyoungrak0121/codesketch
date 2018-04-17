package org.spring.code.vo.dto;

public class JqgridDto {

    private PagingDto pagingDto;
    private Object data;     // Json
    
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	   
    public PagingDto getPagingDto() {
		return pagingDto;
	}
	public void setPagingDto(PagingDto pagingDto) {
		this.pagingDto = pagingDto;
	}
}
