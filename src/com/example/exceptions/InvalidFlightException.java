package com.example.exceptions;

public class InvalidFlightException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidFlightException() {
		super();
	}
	
	public InvalidFlightException(String message) {
		super(message);
	}
	
	public InvalidFlightException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public InvalidFlightException(Throwable cause) {
        super(cause);
    }
}
