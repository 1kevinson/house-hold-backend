package com.project.household.api.Exception;

@SuppressWarnings("serial")
public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException(Integer id) {
		super("Could not find User with Id : " + id);
	}
}
