package com.project.household.api.Enumeration;

public enum TenantStatus {
	ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), DEACTIVATED("DEACTIVATED");

	private final String enumString;

	TenantStatus(String string) {
		this.enumString = string;
	}

	public String getEnumString() {
		return enumString;
	}
}
