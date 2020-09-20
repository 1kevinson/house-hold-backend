package com.project.household.api.Enumeration;

public enum UserAccountStatus {
	VERIFIED("VERIFIED"), UNVERIFIED("UNVERIFIED");

	private final String enumString;

	UserAccountStatus(String string) {
		this.enumString = string;
	}

	public String getEnumString() {
		return enumString;
	}
}
