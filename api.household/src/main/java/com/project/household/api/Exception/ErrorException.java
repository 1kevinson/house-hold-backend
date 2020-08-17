package com.project.household.api.Exception;

@SuppressWarnings("serial")
public class ErrorException extends RuntimeException {
	public ErrorException(String message) {
		super("Error message : " + message);
	}
}
