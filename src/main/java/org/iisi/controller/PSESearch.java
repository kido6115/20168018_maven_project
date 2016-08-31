package org.iisi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "psesearch")
@SessionScoped
public class PSESearch implements Serializable {
	private static final long serialVersionUID = -2322823282417821899L;
	private static final Logger LOGGER = LoggerFactory.getLogger(PSESearch.class);

	private String[] kindConditions;
	private String[] statusConditions;
	private Date startDate = new Date();
	private Date endDate = new Date();

	public String empPSESearch() {
		for (int i = 0; i < kindConditions.length; i++) {
			LOGGER.debug("Kind values are " + kindConditions[i]);
		}
		for (int i = 0; i < statusConditions.length; i++) {
			LOGGER.debug("Status values are " + statusConditions[i]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ");

		LOGGER.debug(sdf.format(startDate));
		LOGGER.debug(sdf.format(endDate));


		return "empsearchPSE.xhtml";

	}

	public String[] getKindConditions() {
		return kindConditions;
	}

	public void setKindConditions(String[] kindConditions) {
		this.kindConditions = kindConditions;
	}

	public String[] getStatusConditions() {
		return statusConditions;
	}

	public void setStatusConditions(String[] statusConditions) {
		this.statusConditions = statusConditions;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
