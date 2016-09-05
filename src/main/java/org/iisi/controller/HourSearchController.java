package org.iisi.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.iisi.bean.SearchHourEmp;
import org.iisi.db.JDBCSearchHour;

@ManagedBean(name = "hoursearch")
@SessionScoped
public class HourSearchController implements Basic {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	private String year;
	private List<SearchHourEmp> searchHourEmpList;

	public String empHourSearch() {

		JDBCSearchHour sh = new JDBCSearchHour();

		List<SearchHourEmp> list = sh.SearchHour_e(year, eid, dept);
		LOGGER.debug(year);
		LOGGER.debug(eid);
		LOGGER.debug(dept);

		for (SearchHourEmp searchhour : list) {

			LOGGER.debug(searchhour.getEid() + " ");
			LOGGER.debug(searchhour.getCredit());
			LOGGER.debug(searchhour.getName() + " ");
			LOGGER.debug(searchhour.getKname());
			LOGGER.debug(searchhour.getYear());

		}
		searchHourEmpList = list;
		return "empsearchhour.xhtml";

	}

	public List<SearchHourEmp> getSearchHourEmpList() {
		return searchHourEmpList;
	}

	public void setSearchHourEmpList(List<SearchHourEmp> searchHourEmpList) {
		this.searchHourEmpList = searchHourEmpList;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
