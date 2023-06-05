package com.fssa.learnJava.project.taskapp.exceptions;

public class DaoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 424307628927676856L;

	public DaoException(String msg) {
		super (msg);
	}
	public DaoException(Throwable ex) {
		super (ex);
	}
	public DaoException(String msg, Throwable ex) {
		super (msg,ex);
	}
}
