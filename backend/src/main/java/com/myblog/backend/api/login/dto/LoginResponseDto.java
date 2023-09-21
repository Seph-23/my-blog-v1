package com.myblog.backend.api.login.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myblog.backend.global.jwt.dto.JwtTokenDto;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class LoginResponseDto {

	private String grantType;

	private String accessToken;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date accessTokenExpirationTime;

	private String refreshToken;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date refreshTokenExpirationTime;

	public static LoginResponseDto of(JwtTokenDto jwtTokenDto) {
		return LoginResponseDto.builder()
			.grantType(jwtTokenDto.getGrantType())
			.accessToken(jwtTokenDto.getAccessToken())
			.accessTokenExpirationTime(jwtTokenDto.getAccessTokenExpirationTime())
			.refreshToken(jwtTokenDto.getRefreshToken())
			.refreshTokenExpirationTime(jwtTokenDto.getRefreshTokenExpirationTime())
			.build();
	}
}
