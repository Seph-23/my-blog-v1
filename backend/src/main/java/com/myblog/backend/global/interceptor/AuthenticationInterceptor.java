package com.myblog.backend.global.interceptor;

import static com.myblog.backend.global.error.ErrorCode.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.myblog.backend.global.error.exception.AuthenticationException;
import com.myblog.backend.global.jwt.constant.TokenType;
import com.myblog.backend.global.jwt.service.TokenManager;
import com.myblog.backend.global.util.AuthorizationHeaderUtils;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

	private final TokenManager tokenManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		// Authorization Header 검증
		String authorizationHeader = request.getHeader("Authorization");
		AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);

		// 토큰 검증
		String token = authorizationHeader.split(" ")[1];
		tokenManager.validateToken(token);

		// 토큰 타입 검증
		Claims tokenClaims = tokenManager.getTokenClaims(token);
		String tokenType = tokenClaims.getSubject();
		if (!TokenType.isAccessToken(tokenType)) {
			throw new AuthenticationException(NOT_ACCESS_TOKEN_TYPE);
		}
		return true;
	}
}
