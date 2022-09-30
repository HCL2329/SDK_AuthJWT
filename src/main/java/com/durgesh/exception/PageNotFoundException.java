package com.durgesh.exception;

public class PageNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public PageNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	

	

}
