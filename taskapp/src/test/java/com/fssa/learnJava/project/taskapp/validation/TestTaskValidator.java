package com.fssa.learnJava.project.taskapp.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.learnJava.project.taskapp.exceptions.InvalidTaskException;
import com.fssa.learnJava.project.taskapp.exceptions.ServiceException;
import com.fssa.learnJava.project.taskapp.model.Task;
import com.fssa.learnJava.project.taskapp.model.TaskPriority;
import com.fssa.learnJava.project.taskapp.model.TaskStatus;

public class TestTaskValidator {
	// Sending a valid task and
	// checking if the TaskValidator is working correctly

	@Test
	public void testValidTask() {

		try {
			TaskValidator taskValidator = new TaskValidator();
			Task task = new Task();

			task.setId(1);
			task.setName("First Task");
			task.setCreateDate(LocalDate.now());
			task.setPriority(TaskPriority.MEDIUM);
			task.setStatus(TaskStatus.STARTED);
			task.setEstimatedNumberOfHrs(2.0);
			assertTrue(taskValidator.validate(task));
		} catch (InvalidTaskException e) {
			fail("Exception while tyring to validate task");
		}
	}
	
	@Test
	public void testNullTaskValidation() {

		try {
			TaskValidator taskValidator = new TaskValidator();
			Task task = null;
			taskValidator.validate(task);
		} catch (InvalidTaskException e) {
			assertEquals("Null task is being passed", e.getMessage());
		}
	}

	
	@Test
	public void testInvalidTaskIDCheck() {

		try {
			TaskValidator taskValidator = new TaskValidator();
			Task task = new Task();

			task.setId(-1);
			task.setName("First Task");
			task.setCreateDate(LocalDate.now());
			task.setPriority(TaskPriority.MEDIUM);
			task.setStatus(TaskStatus.STARTED);
			task.setEstimatedNumberOfHrs(2.0);
			taskValidator.validate(task);
		} catch (InvalidTaskException e) {
			assertEquals("Id cannot be Zero or negative", e.getMessage());
		}
	}
	
	@Test
	public void testInvalidTaskNameCheck() {

		try {
			TaskValidator taskValidator = new TaskValidator();
			Task task = new Task();

			task.setId(-1);
			task.setName("");
			task.setCreateDate(LocalDate.now());
			task.setPriority(TaskPriority.MEDIUM);
			task.setStatus(TaskStatus.STARTED);
			task.setEstimatedNumberOfHrs(2.0);
			taskValidator.validate(task);
		} catch (InvalidTaskException e) {
			assertEquals("Name cannot be empty", e.getMessage());
		}
	}

}
