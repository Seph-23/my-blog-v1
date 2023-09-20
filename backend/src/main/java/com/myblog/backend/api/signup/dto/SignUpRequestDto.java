package com.myblog.backend.api.signup.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpRequestDto {

	private String email;
	private String password;
	private String nickname;
	private String role;
	private String memberType;
}
