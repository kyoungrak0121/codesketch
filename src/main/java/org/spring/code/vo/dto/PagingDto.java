package org.spring.code.vo.dto;

public class PagingDto extends GenericDto {
	
	private static final long serialVersionUID = 1420635747715993129L;
	
	// input
    private int page = -1;
    private int pageSize = -1;
    private int recordCount = -1 ; 
    
    // calculate
    private int totalPage = -1 ; // totalpage
    private int startIndex = -1 ;
    private int endIndex = -1;
   
    
    public void calculatePagingIndex() {
    	this.startIndex = this.page == 1 ? 0 : (this.pageSize * (this.page - 1));
		this.endIndex   = this.page == 1 ? this.pageSize : this.pageSize * this.page; 
    }
    
    public void calculateTotalPage() {
		this.totalPage  = this.pageSize <= 0 ? 0 :(int)Math.ceil((double) this.recordCount / this.pageSize) ;    
    }
    
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

}
