package org.iisi.db;

import java.sql.*;
import java.util.*;

import org.iisi.bean.ViewSub;
import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCEdit extends JDBCCore {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	public List<ViewSub> view_sub(String Pid) {

		ArrayList<ViewSub> sh = new ArrayList<ViewSub>();
		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"Select  PID,PCID,to_char(STARTDATETIME,'yyyy/mm/dd'),to_char(STARTDATETIME,'HH24:mi'),to_char(ENDDATETIME,'yyyy/mm/dd'),to_char(ENDDATETIME,'HH24:mi'),PCTOTAL , KID,PS from PSE_SUb where pid=? order by pcid DESC");

			pstmt.setString(1, Pid);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int i = 0;
				String sPid = rs.getString(1);
				String sPcid = rs.getString(2);

				String sStarDate = rs.getString(3);
				String sStartTime = rs.getString(4);

				String sEndDate = rs.getString(5);
				String sEndTime = rs.getString(6);
				String sPctatol = rs.getString(7);
				String skid = rs.getString(8);
				String sPS = rs.getString(9);

				ViewSub dl = new ViewSub(sPid, sPcid, sStartTime, sStarDate, sEndTime, sEndDate, sPctatol, skid, sPS);// bean裡有,這裡就要有
				sh.add(i, dl);
				i++;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return sh;
	}

}
