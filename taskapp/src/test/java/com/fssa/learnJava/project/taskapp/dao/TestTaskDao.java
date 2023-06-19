/**
 * 
 */
package com.fssa.learnJava.project.taskapp.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.fssa.learnJava.project.taskapp.exceptions.DaoException;
import com.fssa.learnJava.project.taskapp.model.Task;
import com.fssa.learnJava.project.taskapp.model.TaskPriority;
import com.fssa.learnJava.project.taskapp.model.TaskStatus;
import com.fssa.learnJava.project.taskapp.validation.TaskValidator;

/**
 * Test cases for testing the TaskDao Class
 * @author BharathwajSoundarara
 *
 */
public class TestTaskDao {
	/**
	 * Testing the add task method in the DAO Layer
	 */
	@Test
	public void testAddTask() {
		TaskDao taskDao = new TaskDao();
		Task task = new Task();
		task.setId(1);
		//Keeping the task unique by appending the SystemTime
		task.setName("First Task Tester " + System.nanoTime()); 
		task.setCreateDate(LocalDate.now());
		task.setPriority(TaskPriority.MEDIUM);
		task.setStatus(TaskStatus.STARTED);
		task.setEstimatedNumberOfHrs(2.0);
		try {
			assertTrue(taskDao.addTask(task));
		} catch (DaoException e) {
			e.printStackTrace();
			fail("Error while testing add task");
		}
	}
}
