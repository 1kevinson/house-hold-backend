package com.project.household.api.Enumeration;

public enum AppointmentStatus {
	ACCEPTED("ACCEPTED"), REFUSED("REFUSED");

	private final String enumString;

	AppointmentStatus(String string) {
		this.enumString = string;
	}

	public String getEnumString() {
		return enumString;
	}
}
