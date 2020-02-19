/***********************************************************************
 * Module:  SortingDirection.java
 * Author:  Mauricio Saca
 * Purpose: Defines the Class PenaltyPeriodicity
 ***********************************************************************/
package dev.devzero.api.util.enums;

public enum SortingDirection {

	ASCENDING("asc", "ASCENDING"),

	DESCENDING("desc", "DESCENDING");

	String code;

	String description;

	private SortingDirection(final String code, final String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static SortingDirection getSortingDirection(final String code) {
		SortingDirection ret = null;
		for (SortingDirection activeEnum : values()) {
			if (activeEnum.getCode().equals(code)) {
				ret = activeEnum;
				break;
			}
		}
		return ret;
	}

}
