package com.myblog.backend.api.signup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.backend.api.signup.dto.SignUpRequestDto;
import com.myblog.backend.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignUpController {

	private final MemberService memberService;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
		memberService.memberSignUp(signUpRequestDto);
		return ResponseEntity.ok("Ok");
	}
}
