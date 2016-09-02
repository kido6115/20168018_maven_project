package org.iisi.db;

import java.sql.*;
import java.util.*;

import org.iisi.bean.ViewSub;
import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCEditView extends JDBCCore {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	public List<ViewSub> view_sub(String pid) {// (String pid)傳進來的東西

		ArrayList<ViewSub> sh = new ArrayList<ViewSub>();
		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"Select a.PID,a.PCID,to_char(a.STARTDATETIME,'yyyy/mm/dd'),to_char(a.STARTDATETIME,'HH24:MI'),to_char(a.ENDDATETIME,'yyyy/mm/dd'),to_char(a.ENDDATETIME,'HH24:MI'),a.PCTOTAL , b.KNAME,a.PS from PSE_SUb a,PSE_KIND b where b.KID=a.KID and pid=? order by 2 desc");

			pstmt.setString(1, pid);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int i = 0;
				String sPid = rs.getString(1);
				String sPcid = rs.getString(2);

				String sStartDate = rs.getString(3);
				String sStartTime = rs.getString(4);

				String sEndDate = rs.getString(5);
				String sEndTime = rs.getString(6);
				String sPctatol = rs.getString(7);
				String skid = rs.getString(8);
				String sPS = rs.getString(9);

				ViewSub dl = new ViewSub(sPid, sPcid, sStartTime, sStartDate, sEndTime, sEndDate, sPctatol, skid, sPS);// bean裡有,這裡就要有
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
