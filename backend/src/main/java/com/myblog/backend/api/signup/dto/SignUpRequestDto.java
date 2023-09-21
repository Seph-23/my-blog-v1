package com.myblog.backend.api.signup.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpRequestDto {

	@NotNull
	private String email;

	@NotNull
	private String password;

	@NotNull
	private String nickname;

	@NotNull
	private String role;

	@NotNull
	private String memberType;
}
