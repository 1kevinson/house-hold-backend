package com.project.household.api.Exception;

@SuppressWarnings("serial")
public class ErrorException extends RuntimeException {
	// Use for custom exception messages
	public ErrorException(String message) {
		super("Error message : " + message);
	}
}
