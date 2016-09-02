package org.iisi.db;

import java.sql.*;
import java.util.*;

import org.iisi.bean.Edit;
import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditJDBC extends JDBCCore {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	public List<Edit> edit(String eid) throws Exception {
		ArrayList<Edit> showed = new ArrayList<Edit>();

		try {
			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"select pid,applytime,reply from pse_main where eid=? and status=2 order by pid desc");
			pstmt.setString(1, eid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int i = 0;
				String rspid = rs.getString(1);
				String rstime = rs.getString(2);
				String rsreply = rs.getString(3);

				org.iisi.bean.Edit edl = new org.iisi.bean.Edit(rspid, rstime, rsreply);
				showed.add(i, edl);// ArrayList<edit> showed = new
									// ArrayList<edit>();
				i++;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw e;
		}
		return showed;// 回傳editServlet1
	}
}

