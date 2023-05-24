/**
 * 
 */
package com.fssa.learnJava.project.taskapp.driver;

import com.fssa.learnJava.project.taskapp.domain.User;
import com.fssa.learnJava.project.taskapp.services.LoginService;

/**
 * @author BharathwajSoundarara
 *
 */
public class TestLogin {
	public static void main(String[] args) throws Exception{
		LoginService loginService = new LoginService();
		User user = new User();
		user.setName("bharathwaj");
		user.setPassword("mypassword*(");
		System.out.println(loginService.login(user));
	}

}
