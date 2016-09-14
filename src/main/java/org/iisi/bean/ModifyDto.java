package org.iisi.bean;

import java.io.Serializable;
import java.util.Date;

public class ModifyDto implements Serializable{
	private static final long serialVersionUID = -2322823282417821899L;
private CkeckSub checkSub; 
private Date startDate;
private Date endDate;
private String startTime;
private String endTime;
private String kid;
private int hour;
private String ps;
public ModifyDto(CkeckSub checkSub,Date startDate,Date endDate,String startTime,String endTime,String kid,int hour,String ps){
	super();
	this.checkSub=checkSub;
	this.startDate=startDate;
	this.endDate=endDate;
	this.startTime=startTime;
	this.endTime=endTime;
	this.kid=kid;
	this.hour=hour;
	this.ps=ps;
}
public ModifyDto(){
	super();
}
public CkeckSub getCheckSub() {
	return checkSub;
}
public void setCheckSub(CkeckSub checkSub) {
	this.checkSub = checkSub;
}
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getKid() {
	return kid;
}
public void setKid(String kid) {
	this.kid = kid;
}
public int getHour() {
	return hour;
}
public void setHour(int hour) {
	this.hour = hour;
}
public String getPs() {
	return ps;
}
public void setPs(String ps) {
	this.ps = ps;
}




}
