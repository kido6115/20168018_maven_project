package org.iisi.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.SearchHour;
import org.iisi.db.JDBCSetHour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "sethour")
@SessionScoped
public class SethourController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SethourController.class);
	private String year;
	private String[] kindConditions;

	private String eid;
	private String name;

	private List<SearchHour> searchHourList;

	public String searchHour() {
		String sick = "";
		String death = "";
		String birth = "";
		String spe = "";

		LOGGER.debug(eid);
		LOGGER.debug(year);
		for (int i = 0; i < kindConditions.length; i++) {
			if (kindConditions[i].equals("2")) {
				sick = "2";

			} else if (kindConditions[i].equals("3")) {
				death = "3";
			} else if (kindConditions[i].equals("4")) {
				birth = "4";
			} else if (kindConditions[i].equals("5")) {
				spe = "5";
			}
		}
		LOGGER.debug(sick);
		LOGGER.debug(death);
		LOGGER.debug(birth);
		LOGGER.debug(spe);
		JDBCSetHour set = new JDBCSetHour();
		searchHourList = set.Searchhour(year, eid, name, sick, death, birth, spe);
		for (SearchHour list : searchHourList){
			LOGGER.debug(ToStringBuilder.reflectionToString(list));
		}

		return null;
	}

	public List<SearchHour> getSearchHourList() {
		return searchHourList;
	}

	public void setSearchHourList(List<SearchHour> searchHourList) {
		this.searchHourList = searchHourList;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String[] getKindConditions() {
		return kindConditions;
	}

	public void setKindConditions(String[] kindConditions) {
		this.kindConditions = kindConditions;
	}

}
