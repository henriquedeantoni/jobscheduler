package com.jobscheduler.project.entities.enums;

public enum UserStatus {
	ACTIVE(1),
	INACTIVE(2),
	ONAPPROVAL(3),
	SUSPENDED(4),
	EXCLUDED(5);
	
	private int code;
	
	private UserStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static UserStatus valueOf(int code) {
		for (UserStatus value : UserStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code informed.");
	}
}
