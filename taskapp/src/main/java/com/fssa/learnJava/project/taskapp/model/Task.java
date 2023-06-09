package com.fssa.learnJava.project.taskapp.model;

import java.time.LocalDate;

/**
 * 
 * A model class for tracking Task
 * 
 * @author BharathwajSoundarara
 *
 */
public class Task {
	private int id;
	private String name;
	// Best Practice: Change the status as an Enum as we now Status have fixed
	// list of values
	private TaskStatus status;
	private LocalDate createDate;
	private TaskPriority priority;
	private double estimatedNumberOfHrs;
	private User createdByUser; // TODO: change the variable as Created by

	// Setters and getters for each of the member variables
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public double getEstimatedNumberOfHrs() {
		return estimatedNumberOfHrs;
	}

	public void setEstimatedNumberOfHrs(double estimatedNumberOfHrs) {
		this.estimatedNumberOfHrs = estimatedNumberOfHrs;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User user) {
		this.createdByUser = user;
	}

}
