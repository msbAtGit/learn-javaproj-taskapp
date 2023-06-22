package com.fssa.learnJava.project.taskapp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fssa.learnJava.project.taskapp.builder.TaskServiceBuilder;
import com.fssa.learnJava.project.taskapp.exceptions.ServiceException;
import com.fssa.learnJava.project.taskapp.model.Task;
import com.fssa.learnJava.project.taskapp.model.TaskPriority;
import com.fssa.learnJava.project.taskapp.model.TaskStatus;
import com.fssa.learnJava.project.taskapp.model.User;
import com.fssa.learnJava.project.taskapp.services.TaskService;
import com.fssa.learnJava.project.taskapp.util.TaskFormattedPrinter;

/**
 * Class for testing all the services provided by the TaskService class
 * 
 * @author BharathwajSoundarara
 *
 */
public class TestTaskService {

	@Test
	public void testAddTask() {

		try {
			TaskService taskService = TaskServiceBuilder.getTaskService();
			Task task = new Task();
			User user = new User();
			user.setEmail("Testpass1135077294297600@freshworks.com");
			user.setId(3);
			user.setName("TestUser1135077294181500");
			user.setPassword("Testpass1135077294279100");

			task.setId(1);
			task.setName(
					"Test Entry for Task Service testing" + System.nanoTime());
			task.setCreateDate(LocalDate.now());
			task.setPriority(TaskPriority.MEDIUM);
			task.setStatus(TaskStatus.STARTED);
			task.setEstimatedNumberOfHrs(2.0);
			task.setCreatedByUser(user);
			assertTrue(taskService.addTask(task));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Exception while tyring to validate task");
		}
	}

	/**
	 * Testing Get all tasks by User
	 */
	@Test
	public void testGetAllTasksByUser() {
		try {
			// Getting TaskService from Builder
			TaskService taskService = TaskServiceBuilder.getTaskService();

			User user = new User();
			user.setEmail("Testpass1135077294297600@freshworks.com");
			user.setId(3);
			user.setName("TestUser1135077294181500");
			user.setPassword("Testpass1135077294279100");
			
			// Call the Service method and asserting the size of the List to be 
			// Non Zero
			List<Task> tasksByUser = taskService.getAllTasksByUser(user);;
			

			TaskFormattedPrinter.printTaskWithUser(tasksByUser);
			if (tasksByUser == null) {
				fail("Failed to get any task");
			}
			assertTrue(tasksByUser.size() > 0);
			
		
		} catch (ServiceException e) {
			// TODO Add logger to printStackTrace
			e.printStackTrace();
			fail("Error while fetching tasks from service");
		}

	}
}
