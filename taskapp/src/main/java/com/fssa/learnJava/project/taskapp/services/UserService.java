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
import com.fssa.learnJava.project.taskapp.model.UserConstants;
import com.fssa.learnJava.project.taskapp.validation.UserValidator;

/**
 * A class for helping User to login and register
 * 
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
			this.userValidator.validate(user);
			fromDb = this.userdao.getUser(user.getName());

			// No User found hence login has failed
			// The above method returns null if no user is found

			if (fromDb == null) {
				return UserConstants.NO_USER_FOUND;
			}

			else if (fromDb.getPassword().equals(user.getPassword())) {
				return UserConstants.SUCCESSFUL;
			} else {
				return UserConstants.INVALID_LOGIN;
			}
		} catch (DaoException | InvalidUserException ex) {
			// TODO: Add logger for stack trace
			throw new ServiceException(ex);
		}

	}

	public String registerUser(User user) throws ServiceException {
		User userFromDb;
		try {
			if (!userValidator.validate(user)) {
				throw new ServiceException(UserConstants.INVALID_USER);
			}
		} catch (InvalidUserException e1) {
			throw new ServiceException(UserConstants.INVALID_USER, e1);
		}

		try {
			userFromDb = userdao.getUserByEmail(user.getEmail());
		} catch (DaoException e) {
			// TODO Add logic to integrate logger to print the stack trace
			throw new ServiceException(e);
		}

		// TODO: Add user_name, email and attributes first before adding logic
		// based
		// business logic
		if (userFromDb != null) {
			return "Email id " + user.getEmail() + " is already registered";
		} else {
			try {
				if (userdao.createUser(user))
					return UserConstants.REG_SUCCESS;
				else
					return UserConstants.REG_FAILED;
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}

	/**
	 * Additional method to validate the user to be used by other service
	 * classes if necessary
	 * 
	 * @param user
	 * @return true if the passed user is valid
	 * @throws ServiceException
	 */
	public boolean validateUser(User user) throws ServiceException {
		try {
			return userValidator.validate(user);
		} catch (InvalidUserException e) {
			throw new ServiceException(e);
		}
	}
}
