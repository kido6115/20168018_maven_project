package org.iisi.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.iisi.bean.Apply;
import org.iisi.db.JDBCCheckApply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "acceptapplychange")
@SessionScoped
public class AcceptApplyChangeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AcceptApplyChangeController.class);
	private String aPID;

	public String accept() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		aPID = params.get("aPID");
		LOGGER.debug(aPID);
		JDBCCheckApply checkapply = new JDBCCheckApply();
		List<Apply> checkApplyList = checkapply.ListUncheckApply(Integer.parseInt(aPID));
		int status = 0;
		for (Apply list : checkApplyList) {
			status = checkapply.PassApply(list.getaPID(), list.getEid(), list.getDep_id(), list.getJob_id(),
					list.getAp_dep_id(), list.getAp_job_id());
		}
		LOGGER.debug(Integer.toString(status));
		return "checkapply.xhmtl";
	}

}
