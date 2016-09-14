package org.iisi.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.iisi.bean.SearchHourMng;
import org.iisi.bean.ViewSub;
import org.iisi.controller.HourSearchController;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCCoreTest {
	private JDBCCore component;
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	@Before
	public void setUp() throws Exception {
		component = new JDBCCore();
	}

	@Test
	public void testMakeConnection() throws Exception {

		Connection conn;
		conn = component.makeConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * from  PSE_MAIN");

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) { 
			String seid = rs.getString(1);
			String sname = rs.getString(2);
			String syear = rs.getString(3);
			String skname = rs.getString(4);
			String scredit = rs.getString(5);

			LOGGER.info("seid:{}", seid);
			LOGGER.info("sname:{}", sname);
			LOGGER.info("syear:{}", syear);
			LOGGER.info("skname:{}", skname);
			LOGGER.info("scredit:{}", scredit);
		}
		rs.close();
		pstmt.close();
		conn.close();

	}

}
