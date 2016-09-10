package org.iisi.bean;

import java.io.Serializable;

public class SearchHourDto implements Serializable{
	private static final long serialVersionUID = -2322823282417821899L;

	private SearchHour data;
	private String credit;

	public SearchHourDto(SearchHour data ,String credit){
		super();
		this.data=data;
		this.credit=credit;
		
	}
	public SearchHourDto(){
		super();
	}
	public SearchHour getData() {
		return data;
	}
	public void setData(SearchHour data) {
		this.data = data;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}


}
