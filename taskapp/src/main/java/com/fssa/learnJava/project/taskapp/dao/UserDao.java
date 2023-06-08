/**
 * 
 */
package com.fssa.learnJava.project.taskapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.learnJava.project.taskapp.exceptions.DaoException;
import com.fssa.learnJava.project.taskapp.exceptions.InvalidUserException;
import com.fssa.learnJava.project.taskapp.exceptions.ValidatorInitializationException;
import com.fssa.learnJava.project.taskapp.model.User;
import com.fssa.learnJava.project.taskapp.validation.UserValidator;

/**
 * @author BharathwajSoundarara
 *
 */
public class UserDao {

	Statement stmt;
	UserValidator userValidator;

	public UserDao() throws DaoException {
		try {
			userValidator = new UserValidator(8);
		} catch (ValidatorInitializationException e) {
			throw new DaoException(e);
		}
	}

	public boolean createUser(User user) throws DaoException {
		// TODO: Inside each method open the connection and then close the connection.
		try {
			userValidator.validate(user);
		} catch (InvalidUserException e1) {
			e1.printStackTrace();
			throw new DaoException("Invalid User passed to DAO Layer", e1);
		}

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO users (user_name, email_id, additional_info, password) VALUES ( ?, ?, ? ,? );";

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, user.getName());
				pst.setString(2, user.getEmail());
				pst.setString(3, "NA");
				pst.setString(4, user.getPassword());
				int rows2 = pst.executeUpdate();
				if (rows2 > 0) {
					return true;
				} else {
					return false;
				}

			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DaoException(e);
		}

	}

	public void updateUser(User user) {

	}

	// TODO: Best practice rename getUser as findByName should be by using column
	// name in the DB
	public User getUser(String userName) throws DaoException {
		// Make it a null and create it only if the row exists
		User userFromDB = null;

		// Its a good practice to use ? instead of harding coding string since due to
		// hard parse vs soft parse
		final String selectQuery = "SELECT user_id,user_name,password,email_id,additional_info FROM users WHERE user_name = ?";
		// Best Practice: To be followed by students: Using Try with resources
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = connection.prepareStatement(selectQuery)) {
				// Step 05: Get the ResultSet
				pst.setString(1, userName);
				try (ResultSet rs = pst.executeQuery()) {

					// Best Practice remove the while loop and use if condition since its a
					// unique row with only 1 value
					// Instead of Iterating the result
					if (rs.next()) {
						userFromDB = new User();
						userFromDB.setId(rs.getInt("user_id"));
						userFromDB.setName(rs.getString("user_name"));
						userFromDB.setPassword(rs.getString("password"));
						userFromDB.setEmail((rs.getString("email_id")));

					}
				}
			}
		} catch (SQLException | ClassNotFoundException sqe) {
			// TODO: Add a logger for the exception trace.
			throw new DaoException(sqe);
		}

		return userFromDB;
	}

	public User getUserByEmail(String email) throws DaoException {

		User userFromDB = null;
		final String selectQuery = "SELECT user_id,user_name,password,email_id,additional_info FROM users WHERE email_id = ?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(selectQuery)) {

				pst.setString(1, email);

				// Step 04: Execute SELECT Query
				try (ResultSet rs = pst.executeQuery()) {

					// Step 06: Iterate the result
					if (rs.next()) {
						
						userFromDB = new User();
						userFromDB.setId(rs.getInt("user_id"));
						userFromDB.setName(rs.getString("user_name"));
						userFromDB.setPassword(rs.getString("password"));
						userFromDB.setEmail((rs.getString("email_id")));

					}
				}
			}
		} catch (SQLException | ClassNotFoundException sqe) {
			throw new DaoException(sqe);
		}
		return userFromDB;
	}

}
