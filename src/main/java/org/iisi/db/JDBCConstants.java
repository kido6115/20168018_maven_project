package org.iisi.db;

public interface JDBCConstants {
	// 存放連線的基本資料
//	String Driver = "oracle.jdbc.OracleDriver";
//	String URI = "jdbc:oracle:thin:@localhost:1521/xe";
//	String USERNAME = "PSE";
//	String PASSWORD = "pse";
	final static String Driver = "org.h2.Driver";
	final static String URI = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=Oracle;INIT=runscript from \'classpath:scripts/jamari.sql\'  charset  \'UTF-8\' ";
	final static String USERNAME = "sa";
	final static String PASSWORD = ""; 
}
