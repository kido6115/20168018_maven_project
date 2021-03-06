
package org.iisi.db;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

//excel
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.iisi.bean.SearchHourEmp;
import org.iisi.bean.SearchHourMng;
import org.iisi.controller.HourSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//

import au.com.bytecode.opencsv.CSVWriter;

public class JDBCSearchHour extends JDBCCore {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	public List<SearchHourMng> SearchHour_m(String year, String eid, String name, String dep) {
		if (dep.equals("0")) {
			dep = null;
		}
		ArrayList<SearchHourMng> sh = new ArrayList<SearchHourMng>();
		try {
			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT A.EID,  C.NAME,  A.YEAR,  B.KNAME,  A.CREDIT FROM HOURS A,  PSE_KIND B,  EMPLOYEE C WHERE A.EID  = C.EID AND B.KID  = A.KID AND (A.YEAR = ? OR ? IS NULL) AND (A.EID  = ? OR ? IS NULL) AND (C.NAME like ?  OR ? IS NULL) and (c.dep_id=? or ? is null) order by A.EID desc");
			pstmt.setString(1, year);
			pstmt.setString(2, year);
			pstmt.setString(3, eid);
			pstmt.setString(4, eid);
			pstmt.setString(5, "%" + name + "%");
			pstmt.setString(6, name);
			pstmt.setString(7, dep);
			pstmt.setString(8, dep);

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
			LOGGER.error(e.getMessage(), e);
		}
		return sh;
	}

	public List<SearchHourEmp> SearchHour_e(String Year, String eid, String dep) {

		ArrayList<SearchHourEmp> sh = new ArrayList<SearchHourEmp>();
		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT A.EID,  C.NAME,  A.YEAR,  B.KNAME,  A.CREDIT FROM HOURS A,  PSE_KIND B,  EMPLOYEE C WHERE A.EID  = C.EID AND B.KID  = A.KID AND (A.YEAR = ? OR ? IS NULL) AND (A.EID  = ?)  order by A.EID desc");
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
			LOGGER.error(e.getMessage(), e);
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
		// final String disk =
		// "C:\\workspace\\DemoIO-0527 V9.0\\WebContent\\WEB-INF\\export\\";
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
		// final String disk =
		// "C:\\workspace\\DemoIO-0527 V9.0\\WebContent\\WEB-INF\\export\\";
		final String disk = "D:\\";
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
			LOGGER.error(e.getMessage(), e);
			return FileStatus.Fail.path(filePath);
		}
		return FileStatus.Success.path(filePath);
	}

	// 匯出excel員工

	public String exporthour_e(List<SearchHourEmp> list) {
		String file = "HOUR.xls";// 檔名
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();// 建立一個Excel活頁簿
			HSSFSheet sheet = workbook.createSheet("TEST"); // 在活頁簿中建立一個Sheet

			// 印出表頭
			Row headRow = sheet.createRow(0);
			String[] head = { "1", "2", "3", "4", "5" };
			for (int i = 0; i < head.length; i++) {
				Cell cell = headRow.createCell(i);
				cell.setCellValue(head[i]);

			}
			// 印出資料
			int i = 1;
			for (SearchHourEmp hour : list) {
				Row dataRow = sheet.createRow(i);

				Cell cell0 = dataRow.createCell(0);
				cell0.setCellValue(hour.getEid());
				Cell cell1 = dataRow.createCell(1);
				cell1.setCellValue(hour.getName());
				Cell cell2 = dataRow.createCell(2);
				cell2.setCellValue(hour.getYear());
				Cell cell3 = dataRow.createCell(3);
				cell3.setCellValue(hour.getKname());
				Cell cell4 = dataRow.createCell(4);
				cell4.setCellValue(hour.getCredit());

				i++;
			}
			// 排版
			sheet.autoSizeColumn((short) 0);
			sheet.autoSizeColumn((short) 1);
			sheet.autoSizeColumn((short) 2);
			sheet.autoSizeColumn((short) 3);
			sheet.autoSizeColumn((short) 4);

			// 暫存位子
			FileOutputStream fOut = new FileOutputStream("D://HOUR.xls");
			workbook.write(fOut);
			fOut.close();
			workbook.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return file;

	}
	// 匯出excel主管

	public String exporthour_m(List<SearchHourMng> list) {
		String file = "HOUR.xls";// 檔名
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();// 建立一個Excel活頁簿
			HSSFSheet sheet = workbook.createSheet("TEST"); // 在活頁簿中建立一個Sheet

			// 印出表頭
			Row headRow = sheet.createRow(0);
			String[] head = { "員工編號", "姓名", "年度", "假種", "上限" };
			for (int i = 0; i < head.length; i++) {
				Cell cell = headRow.createCell(i);
				cell.setCellValue(head[i]);

			}
			// 印出資料
			int i = 1;
			for (SearchHourMng hour : list) {
				Row dataRow = sheet.createRow(i);

				Cell cell0 = dataRow.createCell(0);
				cell0.setCellValue(hour.getEid());
				Cell cell1 = dataRow.createCell(1);
				cell1.setCellValue(hour.getName());
				Cell cell2 = dataRow.createCell(2);
				cell2.setCellValue(hour.getYear());
				Cell cell3 = dataRow.createCell(3);
				cell3.setCellValue(hour.getKname());
				Cell cell4 = dataRow.createCell(4);
				cell4.setCellValue(hour.getCredit());

				i++;
			}
			// 排版
			sheet.autoSizeColumn((short) 0);
			sheet.autoSizeColumn((short) 1);
			sheet.autoSizeColumn((short) 2);
			sheet.autoSizeColumn((short) 3);
			sheet.autoSizeColumn((short) 4);

			// 暫存位子
			FileOutputStream fOut = new FileOutputStream("c://HOUR.xls");
			workbook.write(fOut);
			fOut.close();
			workbook.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return file;

	}

}
