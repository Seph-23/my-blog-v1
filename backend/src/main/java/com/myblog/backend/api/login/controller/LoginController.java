package com.myblog.backend.api.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.backend.api.login.dto.LoginRequestDto;
import com.myblog.backend.api.login.dto.LoginResponseDto;
import com.myblog.backend.api.login.service.LoginService;
import com.myblog.backend.global.common.controller.AbstractController;
import com.myblog.backend.global.common.dto.BusinessResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController extends AbstractController {

	private final LoginService loginService;

	@PostMapping("/login")
	public BusinessResponseDto<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto,
		HttpServletResponse httpServletResponse) {
		LoginResponseDto loginResponseDto = loginService.login(loginRequestDto);
		Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
		cookie.setMaxAge(60 * 60 * 24 * 30);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		httpServletResponse.addCookie(cookie);
		return ok(loginResponseDto);
	}
}
