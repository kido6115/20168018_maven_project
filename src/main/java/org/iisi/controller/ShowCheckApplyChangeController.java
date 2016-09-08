package org.iisi.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.Apply;
import org.iisi.db.JDBCCheckApply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "showcheckapplychange")
@SessionScoped
public class ShowCheckApplyChangeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShowCheckApplyChangeController.class);
	private List<Apply> checkApplyList;

	private String aPID;



	public String showApplyView() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		aPID = params.get("aPID");
		LOGGER.debug(aPID);
		JDBCCheckApply checkapply = new JDBCCheckApply();
		checkApplyList = checkapply.ListUncheckApply(Integer.parseInt(aPID));
		for (Apply list : checkApplyList) {
			LOGGER.debug(ToStringBuilder.reflectionToString(list));
		}
		
		return "checkapplyview.xhtml";
	}

	public List<Apply> getCheckApplyList() {
		return checkApplyList;
	}

	public void setCheckApplyList(List<Apply> checkApplyList) {
		this.checkApplyList = checkApplyList;
	}
	public String getaPID() {
		return aPID;
	}

	public void setaPID(String aPID) {
		this.aPID = aPID;
	}

}
