package com.project.household.api.Exception;

@SuppressWarnings("serial")
public class UserNotFoundExeption extends RuntimeException {
	public UserNotFoundExeption(Integer id) {
		super("Could not find User with Id : " + id);
	}
}
