package com.fssa.learnJava.project.taskapp.validation;

import java.util.regex.Pattern;

import com.fssa.learnJava.project.taskapp.exceptions.InvalidUserException;
import com.fssa.learnJava.project.taskapp.exceptions.ValidatorInitializationException;
import com.fssa.learnJava.project.taskapp.model.User;

public class UserValidator {

	private int minLength;
	
	private static final int defaultLength = 8;
	private final String emailValidationRegEx = "^(.+)@(\\S+)$";
	
	public UserValidator() {
		this.minLength = defaultLength;
	}
	
	
	public UserValidator(int minLength) throws ValidatorInitializationException {
		if (minLength < 0) {
			throw new ValidatorInitializationException("Invalid length");
		} else {
			this.minLength = minLength;
		}
	}

	public boolean validate(User user) throws InvalidUserException {
		if (user == null) {
			throw new InvalidUserException("Empty User");
		}
		if (user.getName() == null) {
			throw new InvalidUserException("User name is empty");
		} else if (user.getName().isEmpty()) {
			throw new InvalidUserException("User name is empty");
		} else if (user.getPassword() == null) {
			throw new InvalidUserException("Password is null");
		} else if (user.getPassword().isEmpty()) {
			throw new InvalidUserException("Password is empty");
		} else if (user.getPassword().length() < this.minLength) {
			throw new InvalidUserException("Password is less expected length of " + this.minLength);
		} else if (!this.validateEmail(user.getEmail())) {
			throw new InvalidUserException("Please enter a valid email");
		} else {
			return true;
		}

	}

	public boolean validateEmail(String emailAddress) {
		if (emailAddress == null) {
			return false;
		} else if (emailAddress.isEmpty()) {
			return false;
		} else {
			return Pattern.compile(emailValidationRegEx).matcher(emailAddress).matches();
		}
	}

}
