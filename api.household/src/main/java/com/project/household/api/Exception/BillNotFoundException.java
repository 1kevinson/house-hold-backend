package com.project.household.api.Exception;

@SuppressWarnings("serial")
public class BillNotFoundException extends RuntimeException {
	public BillNotFoundException(Integer id) {
		super("Could not find Bill with Id : " + id);
	}
}
