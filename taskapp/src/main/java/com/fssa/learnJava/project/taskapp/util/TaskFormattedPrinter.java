package com.fssa.learnJava.project.taskapp.util;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fssa.learnJava.project.taskapp.model.Task;

/**
 * Class to write methods which can print the tasks as need
 * 
 * @author BharathwajSoundarara
 *
 */
public class TaskFormattedPrinter {

	/**
	 * Printing a list of tasks id, Task Name, Priority, Status, Creation Date
	 * 
	 * @param tasks
	 */
	public static void printTaskWithUser(List<Task> tasks)
			throws IllegalArgumentException {
		if (tasks == null) {
			throw new IllegalArgumentException(
					"Task can't " + "be null while printing");
		}

		// TODO: Add Logger and remove System.out
		// TODO: Improve the formatter by find the maximum length.
		// TODO: Get a template library to do the same for formatted output
		System.out.println("--------------------------------------------------"
				+ "-------------------------------------------");
		System.out.printf("%5s %10s %10s %8s %20s %17s", "Task ID", "Task Name",
				"Prioirty", "Created By", "Status", "Created Date");
		System.out.println();
		System.out.println(
				"---------------------------------------------------------------------------------------------");
		// iterates over the list
		for (Task task : tasks) {
			System.out.format("%7s %14s %7s %10s %25s %13s", task.getId(),
					task.getName(), task.getPriority(),
					task.getCreatedByUser().getName(), task.getStatus(),
					task.getCreateDate()
							.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
							.toString());
			System.out.println();
		}
		System.out.println(
				"----------------------------------------------------------------------------------------------");

	}

}
