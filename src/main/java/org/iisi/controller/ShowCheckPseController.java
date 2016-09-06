package org.iisi.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.CkeckSub;
import org.iisi.db.JDBCCheckPSE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "showcheckpse")
@SessionScoped
public class ShowCheckPseController {
	private String pid;
	private String eid;
	private String name;
	private List<CkeckSub> checkSubList;




	private static final Logger LOGGER = LoggerFactory.getLogger(ShowCheckPseController.class);

	public String showView(){

		  FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params = 
	      fc.getExternalContext().getRequestParameterMap();
	      pid =  params.get("pid");
	      eid =  params.get("eid");
	      name =  params.get("name");
	      LOGGER.debug(pid);
	      LOGGER.debug(eid);
	      LOGGER.debug(name);
	      JDBCCheckPSE checkPSE = new JDBCCheckPSE();
	      checkSubList=checkPSE.ListPSE(Integer.parseInt(pid));
	      for(CkeckSub list : checkSubList){
	      LOGGER.debug(ToStringBuilder.reflectionToString(list));
	      }
		return null;
	}
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	public List<CkeckSub> getCheckSubList() {
		return checkSubList;
	}
	public void setCheckSubList(List<CkeckSub> checkSubList) {
		this.checkSubList = checkSubList;
	}
	

}