package org.iisi.db;

import java.sql.*;

import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCChangePwd extends JDBCCore {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	public int ChangePwd(String Eid, String oldPwd, String newPwd) {
		int status = 0;

		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement st = conn.prepareStatement("Select PWD from EMPLOYEE where EID=?");
			st.setString(1, Eid);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getString("PWD").equals(oldPwd) == false) {
					status = 3; // 舊密碼錯誤

				} else if (rs.getString("PWD").equals(oldPwd)) {

					PreparedStatement st2 = conn.prepareStatement("Update Employee set PWD=? where Eid=?");
					st2.setString(1, newPwd);
					st2.setString(2, Eid);

					st2.executeUpdate();

					st2.close();
					status = 4;// 成功修改
				}
			}
			st.close();
			conn.close();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

			status = 5;// 發生錯誤
		}

		return status;
	}
}