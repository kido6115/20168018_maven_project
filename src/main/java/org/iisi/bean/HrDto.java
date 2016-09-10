package org.iisi.bean;

import java.io.Serializable;

public class HrDto implements Serializable {
	private static final long serialVersionUID = -2322823282417821899L;
	private Hr hrList;
	private String deptId;
	private String jobId;

	public HrDto(Hr hrList, String deptId, String jobId) {
		super();
		this.deptId = deptId;
		this.jobId = jobId;
		this.hrList = hrList;

	}
	public HrDto(){
		super();
	}

	public Hr getHrList() {
		return hrList;
	}

	public void setHrList(Hr hrList) {
		this.hrList = hrList;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}


}
