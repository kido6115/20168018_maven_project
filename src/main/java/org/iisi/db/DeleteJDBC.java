package org.iisi.db;

import java.sql.*;

import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteJDBC extends JDBCCore {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	public int checkbox(String[] checkbox) throws Exception {
		int status = 0;
		try {
			for (int i = 0; i < checkbox.length; i++) {

				Connection conn1;
				conn1 = makeConnection();
				PreparedStatement pstmt = conn1.prepareStatement("update pse_main set status='4' where pid=?");
				// st.setString(1, Account);

				pstmt.setInt(1, Integer.parseInt(checkbox[i]));
				ResultSet rs = pstmt.executeQuery();
				status = 1;

				rs.close();
				pstmt.close();
				conn1.close();
				status = 2;
			}
		} catch (Exception e) {
			status = 3;
			LOGGER.error(e.getMessage(), e);

			throw e;

		}
		// 回傳editServlet1
		return status;

	}
}
