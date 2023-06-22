package com.fssa.learnJava.project.taskapp.services;

import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.fssa.learnJava.project.taskapp.dao.TaskDao;
import com.fssa.learnJava.project.taskapp.exceptions.DaoException;
import com.fssa.learnJava.project.taskapp.exceptions.InvalidTaskException;
import com.fssa.learnJava.project.taskapp.exceptions.ServiceException;
import com.fssa.learnJava.project.taskapp.model.Task;
import com.fssa.learnJava.project.taskapp.model.TaskConstants;
import com.fssa.learnJava.project.taskapp.model.User;
import com.fssa.learnJava.project.taskapp.model.UserConstants;
import com.fssa.learnJava.project.taskapp.validation.TaskValidator;
import com.fssa.learnJava.project.taskapp.validation.UserValidator;

/**
 * Adding how to add a new Task
 * 
 * @author BharathwajSoundarara
 *
 */
public class TaskService {

	TaskValidator taskValidator;
	TaskDao taskDao;
	UserService userService;

	public TaskService(TaskValidator taskValidator, TaskDao taskDao,
			UserService userService) {
		// TODO: Add nullity check validation

		this.taskValidator = taskValidator;
		this.taskDao = taskDao;
		this.userService = userService;
	}

	/**
	 * Adds a task with user composed task object Tries to login then validate
	 * and finally call the addtask DAO class
	 * 
	 * @param task
	 * @return true if the task is added successfully
	 * @throws ServiceException when ever the login fails or if there is issue
	 *                          during Dao calls
	 * 
	 */
	public boolean addTask(Task task) throws ServiceException {

		try {
			// Checking if the User has successfully logged if not don't proceed
			// further with the service logic
			if (!UserConstants.SUCCESSFUL
					.equals(this.userService.login(task.getCreatedByUser()))) {
				throw new ServiceException(TaskConstants.UNABLE_TO_ADD);
			}
			taskValidator.validate(task);
			taskDao.addTask(task);
			return true;
		} catch (InvalidTaskException | DaoException e) {
			// TODO: Add a logger to log the stack trace
			e.printStackTrace();
			throw new ServiceException(TaskConstants.UNABLE_TO_ADD, e);

		}
	}

	/**
	 * Get all the tasks by the 
	 * @param user
	 * @return
	 */
	public List<Task> getAllTasksByUser(User user) throws ServiceException {
		
		
		try {
			return taskDao.findTaskByUser(user);
		} catch (DaoException e) {
			// TODO Change this to a logger
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
