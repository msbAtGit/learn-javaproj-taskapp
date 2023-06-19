package com.fssa.learnJava.project.taskapp.builder;

import com.fssa.learnJava.project.taskapp.dao.TaskDao;
import com.fssa.learnJava.project.taskapp.services.TaskService;
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
	 */
	public static TaskService getTaskService() {
		// Creating a TaskValidator
		TaskValidator taskValidator = new TaskValidator();
		TaskDao taskDao = new TaskDao();

		TaskService taskService = new TaskService(taskValidator, taskDao);
		return taskService;
	}
}
