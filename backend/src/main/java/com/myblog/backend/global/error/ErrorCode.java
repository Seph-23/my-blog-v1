package com.myblog.backend.global.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	// Member
	DUPLICATE_MEMBER(HttpStatus.BAD_REQUEST, "M-001", "이미 존재하는 회원입니다."),
	MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "M-002", "존재하지 않는 회원입니다."),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "M-003", "비밀번호가 일치하지 않습니다."),
	PASSWORD_ERROR_COUNT_EXCEEDED(HttpStatus.UNAUTHORIZED, "M-004", "비밀번호 오류 횟수가 초과되었습니다."),
	ACCOUNT_BLOCKED(HttpStatus.FORBIDDEN, "M-005", "비밀번호 오류 횟수 추가로 인하여 계정이 잠겼습니다."),

	// TOKEN,
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "T-001", "만료된 토큰입니다."),
	NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "T-002", "유효하지 않은 토큰입니다."),
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
