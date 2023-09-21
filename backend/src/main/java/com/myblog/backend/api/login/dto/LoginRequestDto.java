package com.myblog.backend.api.login.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class LoginRequestDto {

	@NotNull
	private String email;

	@NotNull
	private String password;

	@NotNull
	private String memberType;

	@NotNull
	private String role;
}
