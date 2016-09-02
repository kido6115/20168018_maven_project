package org.iisi.controller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.iisi.db.JDBCAddPSE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "addpse")
@SessionScoped
public class AddPSE implements Serializable{
	
	private static final long serialVersionUID = -2322823282417821899L;
	private static final Logger LOGGER = LoggerFactory.getLogger(AddPSE.class);
	private Date startDate = new Date();
	private Date endDate = new Date();
	private String startTime;
	private String endTime;
	private String ps;
	private String kind;
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String addpse(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String eid = (String) session.getAttribute("eid");//抓eid
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ");
		java.text.SimpleDateFormat Formatr = new java.text.SimpleDateFormat(
				"yyyy/MM/dd HH/mm");
		java.util.Date currentime = new java.util.Date();
		String nowTime = Formatr.format(currentime);// 抓現在時間
		JDBCAddPSE add = new JDBCAddPSE();	
		int pid = add.nPSE(eid,nowTime);
		int pcid=1;
		String total="10";

		
		
		
		LOGGER.debug(sdf.format(startDate));
		LOGGER.debug(startTime);
		LOGGER.debug(sdf.format(endDate));
		LOGGER.debug(endTime);
		LOGGER.debug(ps);
		LOGGER.debug(kind);
		add.nSubPSE(pid, pcid, kind, sdf.format(startDate), sdf.format(endDate), startTime, endTime, total, ps);
		
		return null;
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
