package com.fssa.learnJava.project.taskapp.builder;

import com.fssa.learnJava.project.taskapp.dao.TaskDao;
import com.fssa.learnJava.project.taskapp.exceptions.ServiceException;
import com.fssa.learnJava.project.taskapp.services.TaskService;
import com.fssa.learnJava.project.taskapp.services.UserService;
import com.fssa.learnJava.project.taskapp.validation.TaskValidator;

/**
 * Class for creating the TaskService Objects
 * 
 * @author BharathwajSoundarara
 *
 */
public class TaskServiceBuilder {
	/**
	 * Creates a TaskService after injecting Validator and Dao Objects
	 * 
	 * @return TaskService
	 * @throws ServiceException 
	 */
	public static TaskService getTaskService() throws ServiceException {
		// Creating a TaskValidator
		TaskValidator taskValidator = new TaskValidator();
		TaskDao taskDao = new TaskDao();
		UserService userService = new UserService();

		TaskService taskService = new TaskService(taskValidator, taskDao,
				userService);
		return taskService;
	}
}
