package org.spring.code.vo.dto;

import java.util.List;

public class ViewDto extends GenericDto {
	
	private static final long serialVersionUID = 1420635747715993129L;
	
	private int viewSeq;
	private String title ;
	private String requestUri ;
	private String filePath ;
	
	private char visible;
	
	private List<SectionDto> sectionDtoList ;

	public int getViewSeq() {
		return viewSeq;
	}

	public void setViewSeq(int viewSeq) {
		this.viewSeq = viewSeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public char getVisible() {
		return visible;
	}

	public void setVisible(char visible) {
		this.visible = visible;
	}

	public List<SectionDto> getSectionDtoList() {
		return sectionDtoList;
	}

	public void setSectionDtoList(List<SectionDto> sectionDtoList) {
		this.sectionDtoList = sectionDtoList;
	}

	
	
	
	
	
	

}
