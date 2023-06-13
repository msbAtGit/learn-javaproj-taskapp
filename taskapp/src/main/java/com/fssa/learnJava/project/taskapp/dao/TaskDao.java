/**
 * 
 */
package com.fssa.learnJava.project.taskapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import com.fssa.learnJava.project.taskapp.exceptions.DaoException;
import com.fssa.learnJava.project.taskapp.exceptions.InvalidTaskException;
import com.fssa.learnJava.project.taskapp.model.Task;
import com.fssa.learnJava.project.taskapp.validation.TaskValidator;

/**
 * A DAO class for adding, removing and updating Task
 * 
 * @author BharathwajSoundarara
 *
 */
public class TaskDao {
	TaskValidator taskValidator = null;

//	DateTimeFormatter dateTimeFormatter = null;
	public TaskDao(TaskValidator taskValidator) {
		this.taskValidator = taskValidator;
//		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
//		dateTimeFormatter = builder.toFormatter();
//		dateTimeFormatter.
	}

	public boolean addTask(Task task) throws DaoException {
		// TODO: Refactor to include User id in Task
		try {
			// TODO: Move the validator should be in service layer
			taskValidator.validate(task);
		} catch (InvalidTaskException e1) {
			throw new DaoException(e1);
		}
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO tasks(task_name, task_status, " + "task_priority, createDate, estdHrs)" +

					" VALUES ( ?, ?, ? ,?, ?);";
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, task.getName());
				pst.setString(2, task.getStatus().toString());
				pst.setString(3, task.getPriority().toString());
				pst.setString(4, task.getCreateDate().toString());
				pst.setDouble(5, task.getEstimatedNumberOfHrs());

				int rows2 = pst.executeUpdate();
				if (rows2 > 0) {
					return true;
				} else {
					return false;
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Change this to logger
			// Refer:
	// https://docs.oracle.com/javase/tutorial/essential/exceptions/chained.html
			e.printStackTrace();
			throw new DaoException("Issue in creating task object", e);
		}

	}
}
