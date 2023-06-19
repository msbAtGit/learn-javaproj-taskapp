package com.fssa.learnJava.project.taskapp.services;

import com.fssa.learnJava.project.taskapp.dao.TaskDao;
import com.fssa.learnJava.project.taskapp.exceptions.DaoException;
import com.fssa.learnJava.project.taskapp.exceptions.InvalidTaskException;
import com.fssa.learnJava.project.taskapp.exceptions.ServiceException;
import com.fssa.learnJava.project.taskapp.model.Task;
import com.fssa.learnJava.project.taskapp.validation.TaskValidator;

/**
 * Adding how to add a new Task
 * @author BharathwajSoundarara
 *
 */
public class TaskService {
	
	TaskValidator taskValidator;
	TaskDao taskDao;
	
	public TaskService(TaskValidator taskValidator, TaskDao taskDao) {
		this.taskValidator = taskValidator; 
		this.taskDao = taskDao;
	}
	public boolean addTask(Task task) throws ServiceException {
		try {
			taskValidator.validate(task);
			taskDao.addTask(task);
			return true;
		}
		catch(InvalidTaskException | DaoException e) {
			// TODO: Add a logger to log the stack trace
			throw new ServiceException("Unable to add task", e);
			
		}
	}
}
