package com.fssa.learnJava.project.taskapp.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.learnJava.project.taskapp.domain.User;
import com.fssa.learnJava.project.taskapp.services.LoginService;

class TestLoginService {

	@Test
	public void testLogin() {
		
		try {
			LoginService ls = new LoginService();
			User user = new User();

			user.setName("bharathwaj");
			user.setPassword("mypassword*(");

			Assertions.assertEquals(ls.login(user), "SUCCESSFUL");
		} catch (Exception e) {
			fail("Exception while tyring to login");
		}
	}
	
	@Test
	public void registerUser() {
		
		try {
			LoginService ls = new LoginService();
			User user = new User();

			user.setName("vinit");
			user.setPassword("vinitgo*(");
			user.setEmail("vinit@freshworks.com");
			Assertions.assertEquals(ls.registerUser(user), "Email id vinit@freshworks.com is already registered");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception while tyring to Registeration");
		}
		
		
	}
}
