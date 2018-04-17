package org.spring.code.vo.dto;

public class MenuDto extends GenericDto {
	
	private static final long serialVersionUID = 1420635747715993129L;
	
	private int menuSeq;
	private int parentMenuSeq;
	private int standard;
	private int depth;
	private int priority;

	private String title ;
	
	//private String filePath ;
	private String linkUrl ;

	private char visible;

	public int getMenuSeq() {
		return menuSeq;
	}

	public void setMenuSeq(int menuSeq) {
		this.menuSeq = menuSeq;
	}

	public int getParentMenuSeq() {
		return parentMenuSeq;
	}

	public void setParentMenuSeq(int parentMenuSeq) {
		this.parentMenuSeq = parentMenuSeq;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	*/

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public char getVisible() {
		return visible;
	}

	public void setVisible(char visible) {
		this.visible = visible;
	}
	
}
