package com.fssa.learnJava.project.taskapp.validation;

import com.fssa.learnJava.project.taskapp.exceptions.InvalidTaskException;
import com.fssa.learnJava.project.taskapp.exceptions.InvalidUserException;
import com.fssa.learnJava.project.taskapp.model.Task;

/**
 * Class for validating of the model Task class attributes
 * 
 * @author BharathwajSoundarara
 *
 */
public class TaskValidator {

	public boolean validate(Task task) throws InvalidTaskException {
		UserValidator userValidator = new UserValidator();

		// TODO: Add code to check if the passed task is valid
		if (task == null)
			throw new InvalidTaskException("Null task is being passed");
		// BEST Practice: Using inverted equals
		else if (task.getName() == null || "".equals(task.getName()))
			throw new InvalidTaskException("Name cannot be empty");
		// Checking each of the attributes if they are null
		else if (task.getPriority() == null)
			throw new InvalidTaskException("Priority can't be null");
		else if (task.getStatus() == null)
			throw new InvalidTaskException("Priority can't be null");
		else if (task.getCreateDate() == null)
			throw new InvalidTaskException("Create date can't be null");
		// ID and EstdNumOfHrs can't be negative or 0
		else if (task.getId() <= 0)
			throw new InvalidTaskException("Id cannot be Zero or negative");
		else if (task.getEstimatedNumberOfHrs() <= 0)
			throw new InvalidTaskException(
					"EstimatedNumberOfHrs cannot be Zero or negative");
		else
			try {
				if (!userValidator.validate(task.getCreatedByUser())) {
					throw new InvalidTaskException("Invalid User");
				}
			} catch (InvalidUserException e) {
				throw new InvalidTaskException("Invalid User", e);
			}
		return true;
	}

}
