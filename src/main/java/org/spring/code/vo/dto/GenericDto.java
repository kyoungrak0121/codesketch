package org.spring.code.vo.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;


public abstract class GenericDto  implements Serializable{
	private static final long serialVersionUID = 1420635747715993129L;
	
	protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	protected LogDto logDto ; 
	
	public GenericDto() {
		logDto = new LogDto();
	}
}
