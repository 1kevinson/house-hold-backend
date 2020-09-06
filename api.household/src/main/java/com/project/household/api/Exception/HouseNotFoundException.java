package com.project.household.api.Exception;

@SuppressWarnings("serial")
public class HouseNotFoundException extends RuntimeException {
	public HouseNotFoundException(Integer id) {
		super("Could not find House with Id : " + id);
	}
}
