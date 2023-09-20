package com.myblog.backend.global.error.exception;

import com.myblog.backend.global.error.ErrorCode;

import lombok.Getter;

@Getter
public class MemberException extends BusinessException {

	public MemberException(ErrorCode errorCode) {
		super(errorCode);
	}
}
