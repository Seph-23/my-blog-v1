package com.myblog.backend.global.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myblog.backend.global.jwt.service.TokenManager;

@Configuration
public class TokenManagerConfig {

	@Value("${token.secret}")
	private String tokenSecret;

	@Value("${token.access-token-expiration-time}")
	private String accessTokenExpirationTime;

	@Value("${token.refresh-token-expiration-time}")
	private String refreshTokenExpirationTime;

	@Bean
	public TokenManager tokenManager() {
		return new TokenManager(tokenSecret, accessTokenExpirationTime, refreshTokenExpirationTime);
	}
}
