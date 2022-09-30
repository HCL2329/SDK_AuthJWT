package com.durgesh.exception;

public class TechnologyNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public TechnologyNotFoundException(String message) {
		super();
		this.message = message;
	}
	public TechnologyNotFoundException() {
		
	}
	

}