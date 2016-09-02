package org.iisi.db;

import java.sql.*;

import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCApplyChange extends JDBCCore {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	public int applyjob(String Eid, String ad, int dep_old, int job_old, int dep_new, int job_new, String ps) {

		int status = 0;
		int APID = 1;
		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement st = conn.prepareStatement("SELECT MAX(APID) FROM APPLY_CHANGE");

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				APID = rs.getInt("MAX(APID)");

			}

			APID = APID + 1;
			st.close();

			status = 1;

			PreparedStatement st2 = conn.prepareStatement("insert into apply_change values(?,?,?,?,?,?,?,3,SYSDATE)");

			st2.setLong(1, APID);
			st2.setString(2, Eid);
			st2.setInt(3, dep_old);
			st2.setInt(4, dep_new);

			st2.setInt(5, job_old);
			st2.setInt(6, job_new);
			st2.setString(7, ps);
			status = 2;
			st2.executeUpdate();
			status = 3;

			st2.close();

			conn.close();

			status = 4;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

		}
		return status;
	}

}
