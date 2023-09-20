package com.myblog.backend.api.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.backend.api.login.dto.LoginRequestDto;
import com.myblog.backend.api.login.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

	private final LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto) {
		return ResponseEntity.ok("Ok");
	}
}
