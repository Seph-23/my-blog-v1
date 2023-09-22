package com.myblog.backend.global.util;

import static com.myblog.backend.global.error.ErrorCode.*;

import org.springframework.util.StringUtils;

import com.myblog.backend.global.error.exception.AuthenticationException;
import com.myblog.backend.global.jwt.constant.GrantType;

public class AuthorizationHeaderUtils {

	public static void validateAuthorization(String authorizationHeader) {

		// authorizationHeader 필수 체크
		Object ErrorCode;
		if (!StringUtils.hasText(authorizationHeader)) {
			throw new AuthenticationException(NOT_EXISTS_AUTHORIZATION);
		}
		// authorizationHeader Bearer 체크
		String[] authorizations = authorizationHeader.split(" ");
		if (authorizations.length < 2 || (!GrantType.BEARER.getType().equals(authorizations[0]))) {
			throw new AuthenticationException(NOT_VALID_BEARER_GRANT_TYPE);
		}
	}
}
