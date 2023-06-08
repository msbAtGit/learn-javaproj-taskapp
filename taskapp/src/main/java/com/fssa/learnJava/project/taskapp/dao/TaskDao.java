/**
 * 
 */
package com.fssa.learnJava.project.taskapp.dao;

import com.fssa.learnJava.project.taskapp.validation.TaskValidator;

/**
 * A DAO class for adding, removing and updating Task
 * @author BharathwajSoundarara
 *
 */
public class TaskDao {
	TaskValidator taskValidator = null;
	
	public TaskDao(TaskValidator taskValidator) {
		this.taskValidator = taskValidator;
	}
	
	
}
