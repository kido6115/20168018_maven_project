package org.iisi.controller;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.iisi.db.JDBCSetHour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "submithour")
@SessionScoped
public class SubmitHourController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubmitHourController.class);

	private String newCredit;

	public String submit() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String eid = params.get("eid");
		String year = params.get("year");
		String kname = params.get("kname");
		JDBCSetHour set = new JDBCSetHour();
		LOGGER.debug(newCredit);
		LOGGER.debug(eid);
		LOGGER.debug(year);
		LOGGER.debug(kname);


		String result=set.submitHour(newCredit, eid, year, kname);
		LOGGER.debug(result);
		return null;
	}

	public String getNewCredit() {
		return newCredit;
	}

	public void setNewCredit(String newCredit) {
		this.newCredit = newCredit;
	}

}
