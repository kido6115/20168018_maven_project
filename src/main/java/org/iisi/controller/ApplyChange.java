package org.iisi.controller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.iisi.db.JDBCApplyChange;
import org.iisi.db.JDBCLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "applychange")
@SessionScoped

public class ApplyChange implements Serializable {
	private static final long serialVersionUID = -2322823282417821899L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplyChange.class);
	private String newDept;
	private String newJob;
	private String ps;
	
	public String applyChange(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String eid = (String) session.getAttribute("eid");
		
		java.text.SimpleDateFormat Formatr = new java.text.SimpleDateFormat(
				"yyyy/MM/dd HH/mm");
		java.util.Date currentime = new java.util.Date();
		String nowtTime = Formatr.format(currentime);// 抓現在時間
		String ps = "1";
		JDBCLogin login = new JDBCLogin();
		JDBCApplyChange applychange = new JDBCApplyChange();
		int jobId = login.getJobId(eid);
	    int deptId = login.getStatus(eid);
		LOGGER.debug("job is "+jobId);
		LOGGER.debug("dept is "+deptId);
		LOGGER.debug("newdept is "+newDept);
		LOGGER.debug("newjob is "+newJob);
		int status = applychange.applyjob(eid, nowtTime, deptId, jobId, Integer.parseInt(newDept), Integer.parseInt(newJob), ps);
		LOGGER.debug("status is "+status);

		return null;
	}
	public String getNewDept() {
		return newDept;
	}
	public void setNewDept(String newDept) {
		this.newDept = newDept;
	}
	public String getNewJob() {
		return newJob;
	}
	public void setNewJob(String newJob) {
		this.newJob = newJob;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	
	

}
