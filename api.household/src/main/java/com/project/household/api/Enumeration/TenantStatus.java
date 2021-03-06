package com.project.household.api.Enumeration;

public enum TenantStatus {
	ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), DEACTIVATED("DEACTIVATED"), UP_TO_DATE("UP TO DATE"), LATE("LATE");

	private final String enumString;

	TenantStatus(String string) {
		this.enumString = string;
	}

	public String getEnumString() {
		return enumString;
	}
}
