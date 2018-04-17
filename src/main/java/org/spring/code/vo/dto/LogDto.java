package org.spring.code.vo.dto;

import java.io.Serializable;

public class LogDto implements Serializable{
	private static final long serialVersionUID = 1420635747715993129L;
	
	// log 용 
	private StringBuilder start_date ;
	private int who_registration_manager_seq ;
	private StringBuilder update_date;
	private int who_update_manager_seq ;
	
	
	public StringBuilder getStart_date() {
		return start_date;
	}
	public void setStart_date(StringBuilder start_date) {
		this.start_date = start_date;
	}
	public int getWho_registration_manager_seq() {
		return who_registration_manager_seq;
	}
	public void setWho_registration_manager_seq(int who_registration_manager_seq) {
		this.who_registration_manager_seq = who_registration_manager_seq;
	}
	public StringBuilder getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(StringBuilder update_date) {
		this.update_date = update_date;
	}
	public int getWho_update_manager_seq() {
		return who_update_manager_seq;
	}
	public void setWho_update_manager_seq(int who_update_manager_seq) {
		this.who_update_manager_seq = who_update_manager_seq;
	}
	
	
}
