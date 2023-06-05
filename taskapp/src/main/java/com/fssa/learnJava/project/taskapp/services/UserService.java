/**
 * 
 */
package com.fssa.learnJava.project.taskapp.services;

import com.fssa.learnJava.project.taskapp.dao.UserDao;
import com.fssa.learnJava.project.taskapp.exceptions.DaoException;
import com.fssa.learnJava.project.taskapp.exceptions.InvalidUserException;
import com.fssa.learnJava.project.taskapp.exceptions.ServiceException;
import com.fssa.learnJava.project.taskapp.exceptions.ValidatorInitializationException;
import com.fssa.learnJava.project.taskapp.model.User;
import com.fssa.learnJava.project.taskapp.validation.UserValidator;

/**
 * A class for helping User to login and register
 * @author BharathwajSoundarara
 *
 */

//Rename LoginService as UserService for generic purpose naming Convention
//Always keep the class names in nouns
public class UserService {

	private UserDao userdao;
	private UserValidator userValidator;
	private final int minPasswordLen = 8;

	public UserService() throws ServiceException {
		try {
			this.userdao = new UserDao();
			this.userValidator = new UserValidator(this.minPasswordLen);
		} catch (DaoException | ValidatorInitializationException e) {
			throw new ServiceException(e);
		}
	}

	public String login(User user) throws ServiceException {

		User fromDb;
		try {
//			this.userValidator.validate(user);
			fromDb = this.userdao.getUser(user.getName());

			// No User found hence login has failed
			// TODO: Do a trimming before doing empty or nullity checks
			if (fromDb.getName() == null || fromDb.getName().equals("")) {
				return "NO USER Found";
			}

			// TODO: Check for nullity check
			else if (fromDb.getPassword().equals(user.getPassword())) {
				return "SUCCESSFUL";
			} else {
				return "Invalid Login Credentials";
			}
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}

	}

	public String registerUser(User user) throws ServiceException {
		User userFromDb;
		try {
			if (!userValidator.validate(user)) {
				throw new ServiceException("invalid User");
			}
		} catch (InvalidUserException e1) {
			throw new ServiceException("Invalid User", e1);
		}

		try {
			userFromDb = userdao.getUserByEmail(user.getEmail());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}

		// TODO: Add user_name, email and attributes first before adding logic based
		// business logic
		if (userFromDb.getEmail() != null && userFromDb.getEmail().equals(user.getEmail())) {
			return "Email id " + user.getEmail() + " is already registered";
		} else {
			try {
				if (userdao.createUser(user))
					return "Registration Successful";
				else
					return "Registration Failed";
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}
}
