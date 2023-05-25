/**
 * 
 */
package com.fssa.learnJava.project.taskapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.learnJava.project.taskapp.model.User;


/**
 * @author BharathwajSoundarara
 *
 */
public class UserDao {

	Connection connection;
	PreparedStatement pst;
	Statement stmt;

	public UserDao() throws Exception {
		connection = ConnectionUtil.getConnection();
		stmt = connection.createStatement();
	}

	public boolean createUser(User user) throws Exception {
		connection = ConnectionUtil.getConnection();
		String query ="INSERT INTO USERS (user_name, email_id, additional_info, password) VALUES ( ?, ?, ? ,? );";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, user.getName());
		pst.setString(2, user.getEmail());
		pst.setString(3, "NA");
		pst.setString(4, user.getPassword());
		int rows2 = pst.executeUpdate();
		if(rows2 > 0)
			return true;
		else 
			return false;
	}

	public void updateUser(User user) {

	}

	public User getUser(String userName) throws SQLException {
		User userFromDB = new User();
		// Step 04: Execute SELECT Query
		final String selectQuery = "SELECT user_id,user_name,password,email_id,additional_info FROM users WHERE user_name = ?";

		PreparedStatement pst = connection.prepareStatement(selectQuery);
		// Step 05: Get the resultset
		pst.setString(1, userName);

		ResultSet rs = pst.executeQuery();

		// Step 06: Iterate the result
		while (rs.next()) {
			userFromDB.setId(rs.getInt("user_id"));
			userFromDB.setName(rs.getString("user_name"));
			userFromDB.setPassword(rs.getString("password"));
			userFromDB.setEmail((rs.getString("email_id")));

		}

		// Step 07: close the connection resources
		rs.close();
		pst.close();
		connection.close();

		return userFromDB;
	}

	public User getUserByEmail(String email) throws SQLException {
		
		User userFromDB = new User();
		// Step 04: Execute SELECT Query
		final String selectQuery = "SELECT user_id,user_name,password,email_id,additional_info FROM users WHERE email_id = ?";

		PreparedStatement pst = connection.prepareStatement(selectQuery);

		pst.setString(1, email);

		ResultSet rs = pst.executeQuery();

		// Step 06: Iterate the result
		while (rs.next()) {
			userFromDB.setId(rs.getInt("user_id"));
			userFromDB.setName(rs.getString("user_name"));
			userFromDB.setPassword(rs.getString("password"));
			userFromDB.setEmail((rs.getString("email_id")));

		}

		// Step 07: close the connection resources
		rs.close();
		pst.close();
		connection.close();

		return userFromDB;
	}

}
