package org.iisi.db;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import org.iisi.bean.SearchHourEmp;
import org.iisi.bean.SearchHourMng;
import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;

public class JDBCApplyChange2 extends JDBCCore {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	public List<SearchHourMng> SearchHour_m(String year, String eid, String name) {

		ArrayList<SearchHourMng> sh = new ArrayList<SearchHourMng>();
		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT A.EID,  C.NAME,  A.YEAR,  B.KNAME,  A.CREDIT FROM HOURS A,  PSE_KIND B,  EMPLOYEE C WHERE A.EID  = C.EID AND B.KID  = A.KID AND (A.YEAR = ? OR ? IS NULL) AND (A.EID  = ? OR ? IS NULL) AND (C.NAME like ?  OR ? IS NULL) order by A.EID desc");
			pstmt.setString(1, year);
			pstmt.setString(2, year);
			pstmt.setString(3, eid);
			pstmt.setString(4, eid);
			pstmt.setString(5, "%" + name + "%");
			pstmt.setString(6, name);

		

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int i = 0;
				String seid = rs.getString(1);
				String sname = rs.getString(2);
				String syear = rs.getString(3);
				String skname = rs.getString(4);
				String scredit = rs.getString(5);

				SearchHourMng dl = new SearchHourMng(seid, sname, syear, skname, scredit);
				sh.add(i, dl);
				i++;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		return sh;
	}

	public List<SearchHourEmp> SearchHour_e(String Year, String eid) {

		ArrayList<SearchHourEmp> sh = new ArrayList<SearchHourEmp>();
		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT A.EID,  C.NAME,  A.YEAR,  B.KNAME,  A.CREDIT FROM HOURS A,  PSE_KIND B,  EMPLOYEE C WHERE A.EID  = C.EID AND B.KID  = A.KID AND (A.YEAR = ? OR ? IS NULL) AND (A.EID  = ?) order by A.EID desc");
			pstmt.setString(1, Year);
			pstmt.setString(2, Year);
			pstmt.setString(3, eid);

		

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int i = 0;
				String seid = rs.getString(1);
				String sname = rs.getString(2);
				String syear = rs.getString(3);
				String skname = rs.getString(4);
				String scredit = rs.getString(5);

				SearchHourEmp dl = new SearchHourEmp(seid, sname, syear, skname, scredit);
				sh.add(i, dl);
				i++;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		return sh;
	}

	public enum FileStatus {
		Success("檔案寫入成功!"), //
		Fail("檔案寫入失敗!");

		final String msg;

		String filePath = "";

		private FileStatus(final String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}

		public FileStatus path(final String filePath) {
			this.filePath = filePath;
			return this;
		}

		public String getFilePath() {
			return filePath;
		}
	}

	public String Array2Csv(List<SearchHourMng> list) {

		// 01.檔案名稱
		// final String disk = "C:\\workspace\\DemoIO-0527
		// V9.0\\WebContent\\WEB-INF\\export\\";
		final String disk = "C:\\";
		final String fileName = "PSE";

		// 02.資料來源
		final List<String[]> source = new ArrayList<String[]>();

		for (int i = 0; i < list.size(); i++) {

			source.add(new String[] { list.get(i).getYear(), list.get(i).getName(), list.get(i).getEid(),
					list.get(i).getKname(), list.get(i).getCredit() });

		}

		// 02.a 檔案名稱(加上隨機ID結尾以避免檔案重複)
		String file = fileName + ".csv";
		// String file = fileName + UUID.randomUUID() + ".csv";

		final String filePath = disk + file;

		// 03.輸出CSV檔案
		final FileStatus status = this.outputCsv(source, filePath);
		System.out.println(status.getMsg());
		System.out.println(status.getFilePath());
		// 04.執行結果 及 檔案位置
		return file;
	}

	public String ArrayCsv(List<SearchHourEmp> list) {

		// 01.檔案名稱
		// final String disk = "C:\\workspace\\DemoIO-0527
		// V9.0\\WebContent\\WEB-INF\\export\\";
		final String disk = "C:\\";
		final String fileName = "PSE";

		// 02.資料來源
		final List<String[]> source = new ArrayList<String[]>();

		for (int i = 0; i < list.size(); i++) {

			source.add(new String[] { list.get(i).getYear(), list.get(i).getName(), list.get(i).getEid(),
					list.get(i).getKname(), list.get(i).getCredit() });

		}

		// 02.a 檔案名稱(加上隨機ID結尾以避免檔案重複)
		String file = fileName + ".csv";
		// String file = fileName + UUID.randomUUID() + ".csv";

		final String filePath = disk + file;

		// 03.輸出CSV檔案
		final FileStatus status = this.outputCsv(source, filePath);
		System.out.println(status.getMsg());
		System.out.println(status.getFilePath());
		// 04.執行結果 及 檔案位置
		return file;
	}

	private FileStatus outputCsv(final List<String[]> source, final String filePath) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(filePath));
			writer.writeAll(source);
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(),e);
			return FileStatus.Fail.path(filePath);
		}
		return FileStatus.Success.path(filePath);
	}

}


