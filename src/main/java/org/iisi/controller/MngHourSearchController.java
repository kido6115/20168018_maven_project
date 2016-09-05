package org.iisi.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.SearchHourMng;
import org.iisi.db.JDBCSearchHour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "mnghoursearch")
@SessionScoped
public class MngHourSearchController implements Basic {
	private static final Logger LOGGER = LoggerFactory.getLogger(MngHourSearchController.class);
	private String year;
	private String emp = "";


	private String name = "";
	List<SearchHourMng> searchHourMngList;

	public String mngHourSearch() {
		JDBCSearchHour sh = new JDBCSearchHour();

		List<SearchHourMng> list = sh.SearchHour_m(year, emp, name, dept);

		LOGGER.debug("Year is " + year);
		LOGGER.debug("EID is " + eid);
		LOGGER.debug("Name is " + name);
		LOGGER.debug("Dept is " + dept);

		for (SearchHourMng searchhour : list){
			LOGGER.debug(ToStringBuilder.reflectionToString(searchhour));
			}
		searchHourMngList=list;
		return "mngsearchhour.xhtml";
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SearchHourMng> getSearchHourMngList() {
		return searchHourMngList;
	}

	public void setSearchHourMngList(List<SearchHourMng> searchHourMngList) {
		this.searchHourMngList = searchHourMngList;
	}
	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

}