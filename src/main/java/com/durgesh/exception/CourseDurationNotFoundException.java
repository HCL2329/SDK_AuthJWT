package com.durgesh.exception;

public class CourseDurationNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public CourseDurationNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
	
}
