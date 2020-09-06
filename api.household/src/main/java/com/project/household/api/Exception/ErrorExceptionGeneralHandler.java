package com.project.household.api.Exception;

@SuppressWarnings("serial")
public class ErrorExceptionGeneralHandler extends RuntimeException {
	// Use for custom exception messages
	public ErrorExceptionGeneralHandler(String message) {
		super("Error message : " + message);
	}
}
