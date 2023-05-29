package com.fssa.learnJava.project.taskapp.validation;

public class ValidatorInitializationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3785994329939220886L;
	public ValidatorInitializationException(String msg) {
		super (msg);
	}
	public ValidatorInitializationException(Throwable t) {
		super(t);
	}
	public ValidatorInitializationException(String msg, Throwable t) {
		super(msg, t);
	}
	
}
