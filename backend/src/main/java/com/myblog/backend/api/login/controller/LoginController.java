package com.myblog.backend.api.login.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.backend.api.login.dto.LoginRequestDto;
import com.myblog.backend.api.login.dto.LoginResponseDto;
import com.myblog.backend.api.login.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

	private final LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		return ResponseEntity.ok(loginService.login(loginRequestDto));
	}
}
