package com.fssa.learnJava.project.taskapp.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.learnJava.project.taskapp.model.User;
import com.fssa.learnJava.project.taskapp.services.LoginService;
import com.fssa.learnJava.project.taskapp.services.exception.ServiceException;

class TestLoginService {

	@Test
	public void testLogin() {
		
		try {
			LoginService ls = new LoginService();
			User user = new User();

			user.setName("bharathwaj");
			user.setPassword("mypassword*(");

			Assertions.assertEquals("SUCCESSFUL",ls.login(user));
		} catch (ServiceException e) {
			fail("Exception while tyring to login");
		}
	}
	
	@Test
	public void registerUser() {
		
		try {
			LoginService ls = new LoginService();
			User user = new User();

			user.setName("vinit");
			user.setPassword("vinit1234");
			user.setEmail("vinit@freshworks.com");
			Assertions.assertEquals("Email id vinit@freshworks.com is already registered",ls.registerUser(user));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception while tyring to Registeration");
		}
		
		
	}
}
