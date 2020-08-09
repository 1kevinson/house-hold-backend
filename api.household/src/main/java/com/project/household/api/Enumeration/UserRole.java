package com.project.household.api.Enumeration;

public enum UserRole {
	ADMIN("admin"), TENANT("tenant"), OWNER("owner");

	private final String enumString;

	UserRole(String string) {
		this.enumString = string;
	}

	public String getEnumString() {
		return enumString;
	}
}
