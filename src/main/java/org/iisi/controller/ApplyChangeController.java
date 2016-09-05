package org.iisi.controller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.iisi.db.JDBCApplyChange;
import org.iisi.db.JDBCLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "applychange")
@SessionScoped

public class ApplyChangeController implements Basic {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplyChangeController.class);
	private String newDeptId;
	private String newJobId;
	private String ps;
	
	public String applyChange(){
		
		java.text.SimpleDateFormat Formatr = new java.text.SimpleDateFormat(
				"yyyy/MM/dd HH/mm");
		java.util.Date currentime = new java.util.Date();
		String nowtTime = Formatr.format(currentime);// 抓現在時間
		
		JDBCLogin login = new JDBCLogin();
		JDBCApplyChange applychange = new JDBCApplyChange();
		int jobId = login.getJobId(eid);
	    int deptId = login.getStatus(eid);
		LOGGER.debug("job is "+jobId);
		LOGGER.debug("dept is "+deptId);
		LOGGER.debug("newdept is "+newDeptId);
		LOGGER.debug("newjob is "+newJobId);
		int status = applychange.applyjob(eid, nowtTime, deptId, jobId, Integer.parseInt(newDeptId), Integer.parseInt(newJobId), ps);
		LOGGER.debug("status is "+status);

		return null;
	}
	public String getNewDept() {
		return newDeptId;
	}
	public void setNewDept(String newDept) {
		this.newDeptId = newDept;
	}
	public String getNewJob() {
		return newJobId;
	}
	public void setNewJob(String newJob) {
		this.newJobId = newJob;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	
	

}
