package org.gotprint.assignment.usernotes.exception;

public class UnauthorizedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5984115483476061577L;
	private static final String message="Unauthorized access to the resource";
	
	public UnauthorizedException() {
		super(message);		
	}

}
