package com.myblog.backend.global.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	// Member
	DUPLICATE_MEMBER(HttpStatus.BAD_REQUEST, "M-001", "이미 존재하는 회원입니다."),
	MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "M-002", "존재하지 않는 회원입니다.")
	;

	private HttpStatus httpStatus;
	private String errorCode;
	private String message;

	ErrorCode(final HttpStatus httpStatus, final String errorCode, final String message) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}
}
