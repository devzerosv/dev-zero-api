/***********************************************************************
 * Module:  RoleEnum.java
 * Author:  Mauricio Saca
 * Purpose: Defines the Class RoleEnum
 ***********************************************************************/
package dev.devzero.api.enums;

public enum RoleEnum {
	
	ADMIN("ROLE_ADMIN", "Administrador"),
	 
	USER("ROLE_USER", "Usuario");
	
	String code;

	String description;

	private RoleEnum(final String code, final String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static RoleEnum getRoleEnum(final String code) {
		RoleEnum ret = null;
        for (RoleEnum activeEnum : values()) {
            if (activeEnum.getCode().equals(code)) {
                ret = activeEnum;
                break;
            }
        }
		return ret;
	}	
}
