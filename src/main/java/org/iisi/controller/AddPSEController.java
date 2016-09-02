package org.iisi.controller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.iisi.db.JDBCAddPSE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;


@ManagedBean(name = "addpse")
@SessionScoped
public class AddPSEController implements Eid {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AddPSEController.class);
	private Date startDate = new Date();
	private Date endDate = new Date();
	private String startTime;
	private String endTime;
	private String ps;
	private String kind;
	private String total;
	
	public String addpse(){
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ");
		java.text.SimpleDateFormat Formatr = new java.text.SimpleDateFormat(
				"yyyy/MM/dd HH/mm");
		java.util.Date currentime = new java.util.Date();
		String nowTime = Formatr.format(currentime);// 抓現在時間
		JDBCAddPSE add = new JDBCAddPSE();	
		int pid = add.nPSE(eid,nowTime);
		int pcid=1;
		

		
		
		
		LOGGER.debug(sdf.format(startDate));
		LOGGER.debug(startTime);
		LOGGER.debug(sdf.format(endDate));
		LOGGER.debug(endTime);
		LOGGER.debug(ps);
		LOGGER.debug(kind);
		LOGGER.debug(total);

		add.nSubPSE(pid, pcid, kind, sdf.format(startDate), sdf.format(endDate), startTime, endTime, total, ps);
		
		return null;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
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
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
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

}
