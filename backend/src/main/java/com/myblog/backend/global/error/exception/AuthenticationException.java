package com.myblog.backend.global.error.exception;

import com.myblog.backend.global.error.ErrorCode;

public class AuthenticationException extends BusinessException {

	public AuthenticationException(ErrorCode errorCode) {
		super(errorCode);
	}
}
