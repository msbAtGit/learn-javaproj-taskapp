package com.fssa.learnJava.project.taskapp.validation;

import java.util.regex.Pattern;

import com.fssa.learnJava.project.taskapp.InvalidUserException;
import com.fssa.learnJava.project.taskapp.model.User;

public class UserValidator {
	
	private int minLength;
	
	private final String emailValidationRegEx = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
	
	public UserValidator(int minLength) throws Exception{
		if (minLength < 0) {
			throw new Exception("Invalid length");
		}
		else {
			this.minLength = minLength;
		}
	}
	
	public boolean validate (User user) throws InvalidUserException {
		if (user == null) {
			throw new InvalidUserException("Empty User");
		}
		if(user.getName() == null) {
			throw new InvalidUserException("User name is empty");
		}
		else if(user.getName().isEmpty()) {
			throw new InvalidUserException("User name is empty");
		}
		else if (user.getPassword() == null ) {
			throw new InvalidUserException("Password is null");
		}
		else if (user.getPassword().isEmpty()) {
			throw new InvalidUserException("Password is empty");
		}
		else if(user.getPassword().length() < this.minLength) {
			throw new InvalidUserException("Password is less expected length");
		}
		else if(this.validateEmail(user.getEmail())) {
			throw new InvalidUserException ("Please enter a valid email");
		}
		else {
			return true;
		}
			
	}
	
	public boolean validateEmail(String emailAddress) {
	    return Pattern.compile(emailValidationRegEx)
	      .matcher(emailAddress)
	      .matches();
	}

}
