package com.fssa.learnJava.project.taskapp.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.learnJava.project.taskapp.exceptions.ServiceException;
import com.fssa.learnJava.project.taskapp.model.User;
import com.fssa.learnJava.project.taskapp.model.UserConstants;
import com.fssa.learnJava.project.taskapp.services.UserService;

class TestLoginService {

	@Test
	public void testLogin() {

		try {
			UserService ls = new UserService();
			User user = new User();

			user.setName("bharath_sound");
			user.setPassword("password007");
			user.setEmail("bharath@freshworks.com");

			Assertions.assertEquals(UserConstants.SUCCESSFUL, ls.login(user));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Exception while tyring to login");
		}
	}

	// TODO: Add Invalid login credentials test case

	@Test
	public void registerUserWithExistingEmail() {

		try {
			UserService ls = new UserService();
			User user = new User();

			user.setName("vinit");
			user.setPassword("vinit1234");
			user.setEmail("vinit@freshworks.com");
			Assertions.assertEquals(
					"Email id vinit@freshworks.com is already registered",
					ls.registerUser(user));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Exception while tyring to Registeration");
		}

	}

	@Test
	public void testCreateUser() {
		try {
			UserService ls = new UserService();
			User user = new User();

			user.setName("TestUser" + System.nanoTime());
			user.setPassword("Testpass" + System.nanoTime());
			user.setEmail("Testpass" + System.nanoTime() + "@freshworks.com");
			Assertions.assertEquals(UserConstants.REG_SUCCESS,
					ls.registerUser(user));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Exception while tyring to Registeration");
		}
	}

	@Test
	public void registerInvalidUser() {
		try {
			UserService ls = new UserService();
			User user = new User();

			user.setName("");
			user.setPassword("");
			user.setEmail("Testpass" + System.nanoTime() + "@freshworks.com");
			ls.registerUser(user);
			fail("Unexcepted success of registration");

		} catch (ServiceException e) {
			// TODO: Change the assertTrue to assertEquals of the e.getMessage()
			Assertions.assertTrue(true);
		}
	}
}
