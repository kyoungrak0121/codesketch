package org.spring.code.vo.dto;

public class CodeDto extends GenericDto{
	
	private static final long serialVersionUID = 1420635747715993129L;
	
	private int codeSeq;
	
	private String type;
	private String seq;
	
	private String code ;
	private String value;
	private String message;
	private String description;
	private String uesd;
	
	
	public int getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(int codeSeq) {
		this.codeSeq = codeSeq;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUesd() {
		return uesd;
	}
	public void setUesd(String uesd) {
		this.uesd = uesd;
	}
	
}
