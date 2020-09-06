package com.project.household.api.Exception.NotFound;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Integer id) {
		super("Could not find User with Id : " + id);
	}
}
