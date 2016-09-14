package org.iisi.db;

import java.sql.*;

import javax.sql.DataSource;

import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.dbcp2.BasicDataSource;

public class JDBCCore implements JDBCConstants {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);
	private static 	BasicDataSource bds =null;
	private	Connection connect = null;
	
	static {
		getDataSource();
	}
	
	// 繼承連線資料做連線
	public Connection makeConnection() throws Exception {
		if(connect != null && !connect.isClosed()){
			LOGGER.info("連線尚未關閉!");
		} else { 
			connect =getDataSource().getConnection();
		}
		return connect;
	}
	
	private static DataSource getDataSource(){
		if (bds == null) {
			bds = new org.apache.commons.dbcp2.BasicDataSource();
			bds.setDriverClassName(Driver);
			bds.setUrl(URI);
			bds.setUsername(USERNAME);
			bds.setPassword(PASSWORD);
			bds.setInitialSize(3);
		}
		return bds ;
	}

}
