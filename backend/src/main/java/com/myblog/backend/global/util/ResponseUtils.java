package com.myblog.backend.global.util;

import java.time.LocalDateTime;

import com.myblog.backend.global.common.dto.BusinessResponseDto;

public class ResponseUtils {

	public static <T> BusinessResponseDto<T> createResponse(Integer httpStatusCode, String httpStatusMessage, T body) {
		return BusinessResponseDto.<T>builder()
			.httpStatusCode(httpStatusCode)
			.httpStatusMessage(httpStatusMessage)
			.body(body)
			.responseDateTime(LocalDateTime.now())
			.build();
	}
}
