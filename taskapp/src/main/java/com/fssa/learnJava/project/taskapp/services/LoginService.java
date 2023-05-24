/**
 * 
 */
package com.fssa.learnJava.project.taskapp.services;

import java.sql.SQLException;

import com.fssa.learnJava.project.taskapp.dao.UserDao;
import com.fssa.learnJava.project.taskapp.domain.User;

/**
 * @author BharathwajSoundarara
 *
 */
public class LoginService {
	
	UserDao userdao;
	
	public LoginService() throws Exception {
		this.userdao = new UserDao();
	}
	
	public String login (User user) throws SQLException {
		
		User fromDb = this.userdao.getUser(user.getName());
		
		//No User found hence login has failed
		if(fromDb.getName() == null || fromDb.getName() == "") {
			return "NO USER Found";
		}
		
		else if(fromDb.getPassword().equals
										(user.getPassword())) {
			return "SUCCESSFUL";
		}
		else {
			return "Invalid Login Credentials";
		}
		
	}
	
	public String registerUser(User user) throws Exception {
		User userFromDb = userdao.getUserByEmail(user.getEmail());
		
		if(userFromDb.getEmail() != null && userFromDb.getEmail().equals(user.getEmail())) {
			return "Email id " + user.getEmail() + " is already registered"; 
		}
		else if (user.getPassword().length() < 8) {
			return "Password length must have minimum 8 characters";
		}
		else {
			if (userdao.createUser(user))
				return "Registration Successful";
			else
				return "Registration Failed";
		}
	}
}
