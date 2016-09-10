package org.iisi.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.HrDto;
import org.iisi.db.JDBCHr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "hrsubmit")
@SessionScoped
public class SubmitHrController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubmitHrController.class);
	private int deptId;
	private int jobId;
	public String submit(List<HrDto> submitList){
		JDBCHr hr = new JDBCHr();
		for (HrDto list:submitList){
		LOGGER.debug(ToStringBuilder.reflectionToString(list));
		}
		LOGGER.debug(hr.hrSubmit(submitList));
		return null;
		
	}
	
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}




}
