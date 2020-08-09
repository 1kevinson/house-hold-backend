package com.project.household.api.Enumeration;

public enum UserRole {
	ADMIN("ADMIN"), TENANT("TENANT"), OWNER("OWNER");

	private final String enumString;

	UserRole(String string) {
		this.enumString = string;
	}

	public String getEnumString() {
		return enumString;
	}
}
