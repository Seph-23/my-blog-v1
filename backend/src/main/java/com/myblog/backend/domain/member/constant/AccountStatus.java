package com.myblog.backend.domain.member.constant;

public enum AccountStatus {

	ACTIVE, BLOCKED, BANNED;

	public static AccountStatus from(String status) {
		return AccountStatus.valueOf(status.toUpperCase());
	}
}
