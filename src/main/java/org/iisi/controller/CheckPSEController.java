package org.iisi.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.CheckPSE;
import org.iisi.db.JDBCCheckPSE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "checkpse")
@SessionScoped
public class CheckPSEController implements Basic {
	private List<CheckPSE> checkPSEList;
	private CheckPSE checked=null;
	


	private static final Logger LOGGER = LoggerFactory.getLogger(CheckPSEController.class);

	public String checkPSE() {
		JDBCCheckPSE cp = new JDBCCheckPSE();
		checkPSEList = cp.ListUnchecked(dept);
		
		for (CheckPSE chechpse : checkPSEList) {
			LOGGER.debug(ToStringBuilder.reflectionToString(chechpse));

		}
		LOGGER.debug(ToStringBuilder.reflectionToString(checked));
	
		return "checkPSE.xhtml";
	}
	

	public List<CheckPSE> getCheckPSEList() {
		return checkPSEList;
	}

	public void setCheckPSEList(List<CheckPSE> checkPSEList) {
		this.checkPSEList = checkPSEList;
	}
	public CheckPSE getChecked() {
		return checked;
	}


	public void setChecked(CheckPSE checked) {
		this.checked = checked;
	}


}
