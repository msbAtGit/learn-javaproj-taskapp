package com.fssa.learnJava.project.taskapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ConnectionUtil class for getting the MySQL Connection
 * 
 * @author BharathwajSoundarara
 *
 */
public class ConnectionUtil {
	
	/**
	 * Making the private constructor to avoid Code Smells
	 */
	private ConnectionUtil() {
		
	}
	/**
	 * Gets the Connection to run SQLs from Java to MySQL
	 * @return Connection to passed configurations
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection()
			throws SQLException, ClassNotFoundException {
		
		// TODO: Remove the hard coding of username and password and use better 
		// TODO: Either configurations or command line arguments to store them
		// TODO: Add cryptography layer if need be
		Connection con = null;
		String url = "jdbc:mysql://101.53.132.234/corejava_demoapp_bharath";
		String userName = "bharath";
		String passWord = "Bharath123";
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, userName, passWord);
		return con;
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs)
			throws SQLException {

		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}
