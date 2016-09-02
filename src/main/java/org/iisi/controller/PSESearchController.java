package org.iisi.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.SearchPSE;
import org.iisi.db.JDBCLogin;
import org.iisi.db.JDBCPSESearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "psesearch")
@SessionScoped
public class PSESearchController implements Eid {
	private static final Logger LOGGER = LoggerFactory.getLogger(PSESearchController.class);

	private String[] kindConditions;
	private String[] statusConditions;
	private Date startDate = new Date();
	private Date endDate = new Date();
    private List<SearchPSE> searchPSEList=null;

    
	
	public String empPSESearch() {
		
		

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
						LOGGER.debug(ToStringBuilder.reflectionToString(searchhour)
								);
						
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
