package org.iisi.controller;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.iisi.bean.Hr;
import org.iisi.bean.HrDto;
import org.iisi.db.JDBCHr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "hrsearch")
@SessionScoped
public class HrSearchController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HrSearchController.class);
	private String name;
	private String eid;
	private String deptId;
	private String jobId;
	private List<Hr> hrList;
	private List<HrDto> submitList;

	public String hrSearch(){
		LOGGER.debug(name);
		LOGGER.debug(eid);
		LOGGER.debug(deptId);
		LOGGER.debug(jobId);
		JDBCHr hr = new JDBCHr();
		try {
			hrList=hr.hr(eid, name, deptId, jobId);
			submitList=new LinkedList<HrDto>();
			for (Hr list:hrList){
				LOGGER.debug(list.getdepName());
				LOGGER.debug(list.getjobName());
				LOGGER.debug(list.getname());
				LOGGER.debug(list.geteid());
			submitList.add(new HrDto(list,list.getdepID(),list.getjobID()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		  LOGGER.error(e.getMessage(),e);
		}
		
	return null;	
	}
	public List<HrDto> getSubmitList() {
		return submitList;
	}
	public void setSubmitList(List<HrDto> submitList) {
		this.submitList = submitList;
	}
	public List<Hr> getHrList() {
		return hrList;
	}
	public void setHrList(List<Hr> hrList) {
		this.hrList = hrList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
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
