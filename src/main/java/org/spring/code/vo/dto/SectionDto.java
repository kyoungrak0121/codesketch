package org.spring.code.vo.dto;

public class SectionDto extends GenericDto {
	
	private static final long serialVersionUID = 1420635747715993129L;
	
	private int sectionSeq;
	private int viewSeq;
	
	private String title ;
	private String filePath ;
	
	private int priority;
	private char visible;
	
	
	public int getSectionSeq() {
		return sectionSeq;
	}
	public void setSectionSeq(int sectionSeq) {
		this.sectionSeq = sectionSeq;
	}
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public char getVisible() {
		return visible;
	}
	public void setVisible(char visible) {
		this.visible = visible;
	}

	
}
