package com.myblog.backend.global.common.controller;

import static com.myblog.backend.global.util.ResponseUtils.*;

import org.springframework.http.HttpStatus;

import com.myblog.backend.global.common.dto.BusinessResponseDto;

public abstract class AbstractController {

	protected <T> BusinessResponseDto<T> ok(T body) {
		return createResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), body);
	}

	protected <T> BusinessResponseDto<T> created(T body) {
		return createResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), body);
	}
}
