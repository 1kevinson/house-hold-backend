package com.project.household.api.Exception;

@SuppressWarnings("serial")
public class RoomNotFoundException extends RuntimeException {
	public RoomNotFoundException(Integer id) {
		super("Could not find Room with Id : " + id);
	}
}
