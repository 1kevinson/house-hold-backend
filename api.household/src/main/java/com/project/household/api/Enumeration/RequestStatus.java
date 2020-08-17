package com.project.household.api.Enumeration;

public enum RequestStatus {
	SENDED("SENDED"), ACCEPTED("ACCEPTED"), REFUSED("REFUSED");

	private final String enumString;

	RequestStatus(String string) {
		this.enumString = string;
	}

	public String getEnumString() {
		return enumString;
	}
}
