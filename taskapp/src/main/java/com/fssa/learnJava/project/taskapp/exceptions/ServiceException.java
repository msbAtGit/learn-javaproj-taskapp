/**
 * 
 */
package com.fssa.learnJava.project.taskapp.exceptions;

/**
 * @author BharathwajSoundarara
 *
 */
public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	public ServiceException(String msg) {
		super(msg);
		
	}
	
	public ServiceException(Throwable ex) {
		super(ex);
		
	}
	
	public ServiceException(String msg,Throwable ex) {
		super(msg,ex);
		
	}
}
