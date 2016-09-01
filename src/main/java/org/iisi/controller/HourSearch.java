package org.iisi.controller;

import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.iisi.bean.SearchHourEmp;
import org.iisi.db.JDBCLogin;
import org.iisi.db.JDBCSearchHour;

@ManagedBean(name = "hoursearch")
@SessionScoped
public class HourSearch implements Serializable {
	private static final long serialVersionUID = -2322823282417821899L;
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearch.class);

	private String year;
	private String eid;
    private List<SearchHourEmp> searchHourEmpList=null;

	public String empHourSearch() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		eid = (String) session.getAttribute("eid");
		
		JDBCLogin login = new JDBCLogin();
		String dept = login.getDep(eid);
		
		JDBCSearchHour sh = new JDBCSearchHour();
		List<SearchHourEmp> list = sh.SearchHour_e(year, eid, dept);

		for (SearchHourEmp searchhour : list) {
			
			LOGGER.debug(searchhour.getEid()+" ");
			LOGGER.debug(searchhour.getCredit());
			LOGGER.debug(searchhour.getName()+" ");
			LOGGER.debug(searchhour.getKname());
			LOGGER.debug(searchhour.getYear());


		}
		searchHourEmpList=list;
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
