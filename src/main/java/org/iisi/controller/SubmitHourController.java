package org.iisi.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import org.iisi.bean.SearchHourDto;

import org.iisi.db.JDBCSetHour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "submithour")
public class SubmitHourController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubmitHourController.class);

	private String newCredit;
	public String submit(List<SearchHourDto> showList) {

		JDBCSetHour set = new JDBCSetHour();
		


		String result=set.submitHour(showList);
		LOGGER.debug(result);
		
		return "sethour.xhtml";
	}

	public String getNewCredit() {
		return newCredit;
	}

	public void setNewCredit(String newCredit) {
		this.newCredit = newCredit;
	}

}
