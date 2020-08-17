package com.project.household.api.Enumeration;

public enum BillStatus {
	PAID("PAID"), INCOMPLETE("INCOMPLETE");

	private final String enumString;

	BillStatus(String string) {
		this.enumString = string;
	}

	public String getEnumString() {
		return enumString;
	}
}
