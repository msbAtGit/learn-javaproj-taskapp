/**
 * 
 */
package com.fssa.learnJava.project.taskapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.learnJava.project.taskapp.dao.exception.DAOException;
import com.fssa.learnJava.project.taskapp.model.User;

/**
 * @author BharathwajSoundarara
 *
 */
public class UserDao {

	Connection connection;
	Statement stmt;

	public UserDao() throws Exception {
		connection = ConnectionUtil.getConnection();
		stmt = connection.createStatement();
	}

	public boolean createUser(User user) throws DAOException {
		
		String query = "INSERT INTO USERS (user_name, email_id, additional_info, password) VALUES ( ?, ?, ? ,? );";
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			pst = connection.prepareStatement(query);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, "NA");
			pst.setString(4, user.getPassword());
			int rows2 = pst.executeUpdate();
			if (rows2 > 0)
				return true;
			else
				return false;
			//Example for multi catch
		} catch (SQLException | ClassNotFoundException e) {
			throw new DAOException(e);
		}
		
	}

	public void updateUser(User user) {

	}

	public User getUser(String userName) throws DAOException {
		User userFromDB = new User();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			// Step 04: Execute SELECT Query
			final String selectQuery = "SELECT user_id,user_name,password,email_id,additional_info FROM users WHERE user_name = ?";

			pst = connection.prepareStatement(selectQuery);
			// Step 05: Get the ResultSet
			pst.setString(1, userName);

			rs = pst.executeQuery();

			// Step 06: Iterate the result
			while (rs.next()) {
				userFromDB.setId(rs.getInt("user_id"));
				userFromDB.setName(rs.getString("user_name"));
				userFromDB.setPassword(rs.getString("password"));
				userFromDB.setEmail((rs.getString("email_id")));

			}
		} catch (SQLException sqe) {
			throw new DAOException(sqe);
		} finally {
			// Step 07: close the connection resources
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}

		return userFromDB;
	}

	public User getUserByEmail(String email) throws DAOException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		User userFromDB = new User();
		try {

			// Step 04: Execute SELECT Query
			final String selectQuery = "SELECT user_id,user_name,password,email_id,additional_info FROM users WHERE email_id = ?";

			pst = connection.prepareStatement(selectQuery);

			pst.setString(1, email);

			rs = pst.executeQuery();

			// Step 06: Iterate the result
			while (rs.next()) {
				userFromDB.setId(rs.getInt("user_id"));
				userFromDB.setName(rs.getString("user_name"));
				userFromDB.setPassword(rs.getString("password"));
				userFromDB.setEmail((rs.getString("email_id")));

			}

		} catch (SQLException sqe) {
			throw new DAOException(sqe);
		}
		// Step 07: close the connection resources
		finally {
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DAOException(e);
			}

		}
		return userFromDB;
	}
	
	@Override
	public void finalize() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
