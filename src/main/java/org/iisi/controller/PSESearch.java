package org.iisi.controller;

import org.iisi.bean.SearchPSE;
import org.iisi.db.JDBCLogin;
import org.iisi.db.JDBCPSESearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "psesearch")
@SessionScoped
public class PSESearch implements Serializable {
	private static final long serialVersionUID = -2322823282417821899L;
	private static final Logger LOGGER = LoggerFactory.getLogger(PSESearch.class);

	private String[] kindConditions;
	private String[] statusConditions;
	private Date startDate = new Date();
	private Date endDate = new Date();
    private List<SearchPSE> searchPSEList=null;

    
	
	public String empPSESearch() {
		String eid;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		eid = (String) session.getAttribute("eid");

		JDBCLogin login = new JDBCLogin();
		String dept = login.getDep(eid);
		
		
		for (int i = 0; i < kindConditions.length; i++) {
			LOGGER.debug("Kind values are " + kindConditions[i]);
		}
		for (int i = 0; i < statusConditions.length; i++) {
			LOGGER.debug("Status values are " + statusConditions[i]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ");

		LOGGER.debug("Start time is "+sdf.format(startDate));
		LOGGER.debug("End time is "+sdf.format(endDate));
		String enddate = sdf.format(endDate);
		String startdate = sdf.format(startDate);
     

		JDBCPSESearch sc = new JDBCPSESearch();
		
			 
			
				try {
					List<SearchPSE> list = sc.SearchPSE_E(eid,startdate, enddate,kindConditions,statusConditions, dept);
					for (SearchPSE searchhour : list) {
						
						LOGGER.debug(searchhour.geteid());
						LOGGER.debug(searchhour.getstarttime());
						LOGGER.debug(searchhour.getendtime());
						LOGGER.debug(searchhour.getkname());
						LOGGER.debug(searchhour.getsname());
						searchPSEList=list;
					}
				} catch (Exception e) {
					LOGGER.debug("fail");
				}
				
		
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
	public List<SearchPSE> getSearchPSEList() {
		return searchPSEList;
	}

	public void setSearchPSEList(List<SearchPSE> searchPSEList) {
		this.searchPSEList = searchPSEList;
	}


}
