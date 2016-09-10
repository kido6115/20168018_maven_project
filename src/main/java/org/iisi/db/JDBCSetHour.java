package org.iisi.db;

import java.sql.*;
import java.util.*;

import org.iisi.bean.SearchHour;
import org.iisi.bean.SearchHourDto;
import org.iisi.bean.UseLeft;
import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCSetHour extends JDBCCore {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	public List<SearchHour> Searchhour(String year, String eid, String name, String sick, String death, String birth,
			String spe) {
		int i = 0;
		String kid = "";
		ArrayList<SearchHour> sh = new ArrayList<SearchHour>();
		try {

			if (sick.equals("")) {
				sick = "";
			} else {
				sick = " AND (  a.KID='2'";
				i++;

			}
			if (spe.equals("")) {
				spe = "";
			} else {
				if (i == 0) {
					spe = " AND ( a.KID='5'";
					i++;
				} else {
					spe = " OR a.KID='5'";
				}
			}
			if (birth.equals("")) {
				birth = "";
			} else {
				if (i == 0) {
					birth = " AND ( a.KID='4'";
					i++;
				} else {
					birth = " OR a.KID='4'";
				}
			}
			if (death.equals("")) {
				death = "";
			} else {
				if (i == 0) {
					death = " AND ( a.KID='3'";
					i++;
				} else {
					death = " OR a.KID='3'";
				}
			}
			kid = sick + spe + birth + death;

			if (i > 0) {
				kid = kid + ")";
			}
			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT A.EID,  C.NAME,  A.YEAR,  B.KNAME,  A.CREDIT FROM HOURS A,  PSE_KIND B,  EMPLOYEE C WHERE A.EID  = C.EID AND B.KID  = A.KID AND (A.YEAR = ? OR ? IS NULL) AND (A.EID  = ? OR ? IS NULL) AND (C.NAME = ? OR ? IS NULL)"
							+ kid + " order by c.eid desc,a.kid desc ");

			pstmt.setString(1, year);
			pstmt.setString(2, year);
			pstmt.setString(3, eid);
			pstmt.setString(4, eid);
			pstmt.setString(5, name);
			pstmt.setString(6, name);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int j = 0;
				String seid = rs.getString(1);
				String sname = rs.getString(2);
				String syear = rs.getString(3);
				String skname = rs.getString(4);
				String scredit = rs.getString(5);

				SearchHour dl = new SearchHour(seid, sname, syear, skname, scredit);
				sh.add(j, dl);
				j++;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return sh;
	}

	public List<UseLeft> SearchUseHour(String status, String year) {

		ArrayList<UseLeft> use = new ArrayList<UseLeft>();
		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT A.EID,C.KID,SUM(C.PCTOTAL),D.KNAME FROM EMPLOYEE A ,PSE_MAIN B ,PSE_SUB C,PSE_KIND D WHERE A.EID=B.EID AND B.PID=C.PID AND C.KID=D.KID AND STATUS="
							+ status + " AND C.STARTDATETIME BETWEEN TO_DATE('" + year
							+ "-01-01','YYYY-MM-DD') AND TO_DATE('" + year
							+ "-12-31','YYYY-MM-DD') GROUP BY A.EID,C.KID,D.KNAME");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int i = 0;
				String seid = rs.getString(1);
				String sname = rs.getString(4);
				String stotal = rs.getString(3);

				UseLeft dl = new UseLeft(seid, sname, stotal);
				use.add(i, dl);
				i++;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return use;
	}
    public String submitHour(List<SearchHourDto> showList){
    	String year;
    	String eid;
    	String kid;
    	try {
    		Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn.prepareStatement("update hours set credit=? where eid=? AND KID=? AND YEAR=?");
    		for (SearchHourDto list : showList){
    			LOGGER.debug(list.getData().getEid());
    			LOGGER.debug(list.getData().getKname());
    			LOGGER.debug(list.getCredit());
    			
    					year = list.getData().getYear();
    					eid = list.getData().getEid();
    					kid = list.getData().getKname();
    					if (kid.equals("病假")) {
    						kid = "2";
    					}
    					if (kid.equals("喪假")) {
    						kid = "3";
    					}
    					if (kid.equals("產假")) {
    						kid = "4";
    					}
    					if (kid.equals("特休")) {
    						kid = "5";
    					}

    					pstmt.setString(1, list.getCredit());
    					pstmt.setString(2, eid);
    					pstmt.setString(3, kid);
    					pstmt.setString(4, year);
    					pstmt.executeUpdate();
    					

    		}
    	}catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
			return "failure";
		}
    	return "success";
    }
	public int SubmitHour(String[] credit, ArrayList<SearchHour> list) {
		String[] update = credit;

		String eid = "";

		String year = "";
		String kid = "";
		String ncredit = "";

		int k = 0;

		ArrayList<SearchHour> sh = new ArrayList<SearchHour>();
		sh = list;

		try {

			Connection conn;
			conn = makeConnection();

			PreparedStatement pstmt = conn.prepareStatement("update hours set credit=? where eid=? AND KID=?");

			if (sh != null) {
				for (SearchHour searchhour : sh) {

					year = searchhour.getYear();
					eid = searchhour.getEid();
					kid = searchhour.getKname();
					ncredit = update[k];
					if (kid.equals("病假")) {
						kid = "2";
					}
					if (kid.equals("喪假")) {
						kid = "3";
					}
					if (kid.equals("產假")) {
						kid = "4";
					}
					if (kid.equals("特休")) {
						kid = "5";
					}

					pstmt.setString(1, ncredit);
					pstmt.setString(2, eid);
					pstmt.setString(3, kid);
					pstmt.executeUpdate();
					k++;

				}

			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return 0;

	}

	public int MaxYear() {
		String year;
		int maxyear = 0;

		try {

			Connection conn;
			conn = makeConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(year) from hours");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				year = rs.getString(1);
				maxyear = Integer.parseInt(year);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

		}
		return maxyear;
	}

	public int AddNextYear(int year, int nyear) {
		String Year = Integer.toString(year);// 今年度
		String NYear = Integer.toString(nyear);// 明年度
		int a = 0;

		try {

			Connection conn;
			conn = makeConnection();

			PreparedStatement pstmt1 = conn
					.prepareStatement("CREATE TABLE HOURS_TEMP  AS SELECT * FROM HOURS where year = " + Year);

			pstmt1.executeQuery();

			PreparedStatement pstmt2 = conn.prepareStatement("UPDATE HOURS_TEMP SET YEAR=? WHERE YEAR=?");
			pstmt2.setString(1, NYear);
			pstmt2.setString(2, Year);
			pstmt2.executeQuery();
			PreparedStatement pstmt3 = conn.prepareStatement("INSERT INTO HOURS SELECT * FROM HOURS_TEMP");
			pstmt3.executeUpdate();
			PreparedStatement pstmt4 = conn.prepareStatement("DROP TABLE HOURS_TEMP");
			pstmt4.executeUpdate();
			a = 1;

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

		}
		return a;
	}
}
