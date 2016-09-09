package org.iisi.controller;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.iisi.db.JDBCSetHour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "addnextyear")
@SessionScoped
public class AddNextYearController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AcceptApplyChangeController.class);

	public String addyear() {
		Date sysdate = new Date();
		JDBCSetHour set = new JDBCSetHour();
		int nowdate = sysdate.getYear() + 1900;
		LOGGER.debug(Integer.toString(nowdate));
		LOGGER.debug(Integer.toString(set.MaxYear()));

		
			int year = set.MaxYear();// HOURS中YEAR最大值
			int nyear = year + 1;//
			int a = set.AddNextYear(year, nyear);
			LOGGER.debug(Integer.toString(a));
		
		
		return null;
	}
}
