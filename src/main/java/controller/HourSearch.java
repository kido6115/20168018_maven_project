package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import bean.SearchHourEmp;
import db.JDBCLogin;
import db.JDBCSearchHour;

@ManagedBean(name = "hoursearch")
@SessionScoped
public class HourSearch implements Serializable {
	private static final long serialVersionUID = -2322823282417821899L;

	private String year;
	private String eid;

	public String empHourSearch() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		eid = (String) session.getAttribute("eid");
		JDBCLogin login = new JDBCLogin();
		String dept = login.getDep(eid);
		JDBCSearchHour sh = new JDBCSearchHour();
		List<SearchHourEmp> list = sh.SearchHour_e(year, eid, dept);

		for (SearchHourEmp searchhour : list) {
			System.out.print(searchhour.getEid()+" ");
			System.out.println(searchhour.getCredit());
			System.out.print(searchhour.getName()+" ");
			System.out.println(searchhour.getKname());

		}

		return "empsearchhour.xhtml";

	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
