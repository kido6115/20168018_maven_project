package org.iisi.controller;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.iisi.db.JDBCCheckApply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ManagedBean(name = "dclineapplychange")
@SessionScoped
public class DeclineApplyChangeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeclineApplyChangeController.class);
	private String aPID;
	
	public String decline(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		 aPID = params.get("aPID");
		LOGGER.debug(aPID);
		JDBCCheckApply checkapply = new JDBCCheckApply();
		int status = checkapply.notPassApply(Integer.parseInt(aPID));

		LOGGER.debug(Integer.toString(status));
		
		return "checkapply.xhmtl";
	}

	public String getaPID() {
		return aPID;
	}

	public void setaPID(String aPID) {
		this.aPID = aPID;
	}

}
