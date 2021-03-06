package org.iisi.db;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.iisi.bean.Hr;
import org.iisi.bean.HrDto;
import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCHr extends JDBCCore {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);
public String hrSubmit(List<HrDto> submitList){
	String result="修改成功";
	Connection conn;
	try {
		conn = makeConnection();
		for (HrDto list : submitList){
			PreparedStatement pstmt = conn.prepareStatement("update employee set job_id=?,dep_id=? where eid=?");
			String eid = list.getHrList().geteid();
			String depId = list.getDeptId();
			String jobId = list.getJobId();

			pstmt.setString(1, jobId);
			pstmt.setString(2, depId);
			pstmt.setString(3, eid);

			pstmt.executeUpdate();

			
		}

	} catch (Exception e) {
		// TODO Auto-generated catch block
		result="修改失敗";
		LOGGER.error(e.getMessage(),e);
	}

	
	return result;
	
}
	public ArrayList<String> hrChange(List<String[]> list) throws Exception {
		ArrayList<String> sh = new ArrayList<String>();

		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs = null;
			String[] ss = null;
			int countManager = 0;

			for (int j = 0; j < list.size(); j++) {
				ss = (String[]) list.get(j);

				if (ss[1].equals(ss[3]) == true && ss[2].equals(ss[4]) == true) {

				} else {
					if (Integer.parseInt(ss[2]) == 2) { // 是主管的話作以下判斷

						pstmt = conn.prepareStatement("SELECT count(job_id) from employee where dep_id=? and job_id=2");
						pstmt.setString(1, ss[1]);
						rs = pstmt.executeQuery();
						if (rs.next()) {

						}
						if (countManager > 1) { // 主管不只一個

							pstmt2 = conn.prepareStatement("update employee set Dep_id=? ,Job_id=? where EID=?");
							pstmt2.setString(1, ss[3]);
							pstmt2.setString(2, ss[4]);
							pstmt2.setString(3, ss[0]);
							pstmt2.executeUpdate();
							pstmt2.close();

						} else {
							sh.add(ss[0]);
						}
						pstmt.close();
						rs.close();
					} else { // 若不是主管坐下面
						pstmt2 = conn.prepareStatement("update employee set Dep_id=? , Job_id=? where EID=?");
						pstmt2.setInt(1, Integer.parseInt(ss[3]));
						pstmt2.setInt(2, Integer.parseInt(ss[4]));
						pstmt2.setString(3, ss[0]);
						pstmt2.executeUpdate();
						pstmt2.close();

					}

				}

			}

			conn.close();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return sh;
	}

	public List<Hr> hr(String heid, String hname, String dep_id, String job_id) throws Exception {
		ArrayList<Hr> sh = new ArrayList<Hr>();

		int i = 0;
		String eid;
		String name;
		String depID;
		String depName;
		String jobID;
		String jobName;

		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(
					"SELECT employee.eid,employee.name,employee.DEP_ID,DEP.DEP_NAME,JOB.JOB_ID ,JOB.JOB_name FROM employee,DEP,JOB where DEP.DEP_ID = employee.DEP_ID and JOB.JOB_ID=employee.JOB_ID and  (employee.eid = ? or ? is null) and (employee.name like ? or ? is null) and (DEP.DEP_ID = ? or ? is null) and (JOB.JOB_ID = ? or ? is null)  and JOB.JOB_ID <>'3' order by employee.eid");

			pstmt.setString(1, heid);
			pstmt.setString(2, heid);
			pstmt.setString(3, "%" + hname + "%");
			pstmt.setString(4, hname);
			pstmt.setString(5, dep_id);
			pstmt.setString(6, dep_id);
			pstmt.setString(7, job_id);
			pstmt.setString(8, job_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				eid = rs.getString(1);
				name = rs.getString(2);
				depID = rs.getString(3);
				depName = rs.getString(4);
				jobID = rs.getString(5);
				jobName = rs.getString(6);

				Hr dl = new Hr(eid, name, depID, depName, jobID, jobName);
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

	public int SubmitHr(ArrayList<Hr> list) {

		ArrayList<Hr> sh = new ArrayList<Hr>();
		sh = list;

		try {

			Connection conn;
			conn = makeConnection();

			PreparedStatement pstmt = conn.prepareStatement("update employee set job_id=?,dep_id=? where eid=?");

			if (sh != null) {
				for (Hr hr : sh) {

					String eid = hr.geteid();
					String depID = hr.getdepID();
					String jobID = hr.getjobID();

					pstmt.setString(1, jobID);
					pstmt.setString(2, depID);
					pstmt.setString(3, eid);

					pstmt.executeUpdate();

				}

			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return 0;

	}

}
