package com.fssa.learnJava.project.taskapp.exceptions;

/**
 * Custom exception to handle cases when invalid Tasks are 
 * Being created or added
 * @author BharathwajSoundarara
 *
 */
public class InvalidTaskException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8105491977357554060L;
	
	// Calling each super constructors for each of the types
	public InvalidTaskException(String msg) {
		super(msg);
	}
	
	public InvalidTaskException(Throwable te) {
		super(te);
	}
	
	public InvalidTaskException(String msg, Throwable te) {
		super(msg, te);
	}
}
