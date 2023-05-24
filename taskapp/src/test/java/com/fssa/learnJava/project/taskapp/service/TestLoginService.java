package com.fssa.learnJava.project.taskapp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

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
}
