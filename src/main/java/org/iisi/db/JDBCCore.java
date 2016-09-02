package org.iisi.db;

import java.sql.*;

import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCCore implements JDBCConstants {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	// 繼承連線資料做連線
	public Connection makeConnection() throws Exception {

		Connection conn;

		try {

			Class.forName(Driver);
			conn = DriverManager.getConnection(URI, USERNAME, PASSWORD);
			return conn;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

	}

}
