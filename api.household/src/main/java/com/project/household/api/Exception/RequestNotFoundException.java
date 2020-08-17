package com.project.household.api.Exception;

@SuppressWarnings("serial")
public class RequestNotFoundException extends RuntimeException {
	public RequestNotFoundException(Integer id) {
		super("Could not find Request with Id : " + id);
	}
}
