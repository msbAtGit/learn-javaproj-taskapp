/**
 * 
 */
package com.fssa.learnJava.project.taskapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import com.fssa.learnJava.project.taskapp.exceptions.DaoException;
import com.fssa.learnJava.project.taskapp.model.Task;
import com.fssa.learnJava.project.taskapp.model.TaskPriority;
import com.fssa.learnJava.project.taskapp.model.TaskStatus;
import com.fssa.learnJava.project.taskapp.model.User;

/**
 * A DAO class for adding, removing and updating Task
 * 
 * @author BharathwajSoundarara
 *
 */
public class TaskDao {
	private static final String DATE_FORMAT_IN_DB = "yyyy-MM-dd";

//	TaskValidator taskValidator = null;
	public TaskDao() {

//	DateTimeFormatter dateTimeFormatter = null;
//	public TaskDao(TaskValidator taskValidator) {
//		this.taskValidator = taskValidator;
//		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
//		dateTimeFormatter = builder.toFormatter();
//		dateTimeFormatter.
	}

	public boolean addTask(Task task) throws DaoException {
		// TODO: Refactor to include User id in Task
//		try {
//			// TODO: Move the validator should be in service layer
//			taskValidator.validate(task);
//		} catch (InvalidTaskException e1) {
//			throw new DaoException(e1);
//		}
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO tasks(task_name, task_status, "
					+ "task_priority, createDate, estdHrs,user_id)" +

					" VALUES ( ?, ?, ? ,?, ?,?);";
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, task.getName());
				pst.setString(2, task.getStatus().toString());
				pst.setString(3, task.getPriority().toString());
				pst.setString(4, task.getCreateDate().toString());
				pst.setDouble(5, task.getEstimatedNumberOfHrs());
				// Getting the user id from the user contained in task
				pst.setInt(6, task.getCreatedByUser().getId());

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

	/**
	 * Accepts a user and fetches all the tasks which matches the user id from
	 * the Database
	 * 
	 * @param user for which we need to get the tasks
	 * @return
	 * @throws DaoException if there is SQLException or other DB related issues
	 */
	public List<Task> findTaskByUser(User user) throws DaoException {
		ArrayList<Task> tasksList = new ArrayList<Task>();
		final String selectQuery = "SELECT id, task_name, task_status,"
				+ "task_priority,createDate,"
				+ "estdHrs,t.user_id as t_user_id,user_name,email_id,password "
				+ "FROM tasks t INNER JOIN users u on (u.user_id = t.user_id) "
				+ "WHERE t.user_id = ?";
		// Best Practice: To be followed by students: Using Try with resources
		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection
					.prepareStatement(selectQuery)) {

				// Get the ResultSet by fixing the user id to the select query
				pst.setInt(1, user.getId());

				try (ResultSet rs = pst.executeQuery()) {

					// Iterating the result and converting each row
					// as a task object
					while (rs.next()) {
						Task taskFromDb = new Task();
						User taskUserFromDb = new User();
						taskFromDb.setId(rs.getInt("id"));
						taskFromDb.setName(rs.getString("task_name"));

						// Eg: on how to convert a result from DB into an ENUM
						taskFromDb.setStatus(TaskStatus
								.valueOf(rs.getString("task_status")));
						taskFromDb.setPriority(TaskPriority
								.valueOf(rs.getString("task_priority")));

						taskFromDb.setCreateDate(this.formatDateyyyyMMdd(
								rs.getString("createDate")));
						taskFromDb.setEstimatedNumberOfHrs(
								rs.getDouble("estdHrs"));
						taskUserFromDb.setId(rs.getInt("t_user_id"));
						taskUserFromDb.setName(rs.getString("user_name"));
						taskUserFromDb.setEmail(rs.getString("email_id"));
						taskUserFromDb.setPassword(rs.getString("password"));
						
						taskFromDb.setCreatedByUser(taskUserFromDb);
						tasksList.add(taskFromDb);

					}
				}
			}
			return tasksList;
		} catch (SQLException | ClassNotFoundException sqe) {
			// TODO: Add a logger for the exception trace.
			throw new DaoException(sqe);
		}

		
	}

	/**
	 * 
	 * 
	 * @param dateFromDB
	 * @return
	 */
	private LocalDate formatDateyyyyMMdd(String dateFromDB) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Converting dateString to LocalDate
		LocalDate parsedDate = LocalDate.parse(dateFromDB, formatter);
		return parsedDate;
	}
}
