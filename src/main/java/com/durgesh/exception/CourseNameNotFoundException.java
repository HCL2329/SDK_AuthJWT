package com.durgesh.exception;

public class CourseNameNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public CourseNameNotFoundException(String message) {
		super();
		this.message = message;
	}

	
}