package com.fssa.learnJava.project.taskapp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.fssa.learnJava.project.taskapp.builder.TaskServiceBuilder;
import com.fssa.learnJava.project.taskapp.exceptions.ServiceException;
import com.fssa.learnJava.project.taskapp.model.Task;
import com.fssa.learnJava.project.taskapp.model.TaskPriority;
import com.fssa.learnJava.project.taskapp.model.TaskStatus;
import com.fssa.learnJava.project.taskapp.services.TaskService;

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

			task.setId(1);
			task.setName(
					"Test Entry for Task Service testing" + System.nanoTime());
			task.setCreateDate(LocalDate.now());
			task.setPriority(TaskPriority.MEDIUM);
			task.setStatus(TaskStatus.STARTED);
			task.setEstimatedNumberOfHrs(2.0);
			assertTrue(taskService.addTask(task));
		} catch (ServiceException e) {
			fail("Exception while tyring to validate task");
		}
	}
}
