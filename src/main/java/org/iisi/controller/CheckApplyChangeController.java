package org.iisi.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.Apply;
import org.iisi.db.JDBCCheckApply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ManagedBean(name = "checkapplychange")
@SessionScoped
public class CheckApplyChangeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckApplyChangeController.class);
	private List<Apply> checkApplyList;

	public String check(){
		JDBCCheckApply checkapply=new JDBCCheckApply();
		 checkApplyList=checkapply.ListUncheckApply(0);
		 for(Apply list : checkApplyList){
			 LOGGER.debug(ToStringBuilder.reflectionToString(list));
			 
		 }
		return null;
	}
	public List<Apply> getCheckApplyList() {
		return checkApplyList;
	}
	public void setCheckApplyList(List<Apply> checkApplyList) {
		this.checkApplyList = checkApplyList;
	}
	


}
