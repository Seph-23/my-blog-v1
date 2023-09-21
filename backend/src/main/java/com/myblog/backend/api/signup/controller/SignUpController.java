package com.myblog.backend.api.signup.controller;

import java.net.URI;

import javax.validation.Valid;

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
	public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
		log.info("signUpRequestDto: {}", signUpRequestDto.toString());
		memberService.validateDuplicateMember(signUpRequestDto);
		Long memberId = memberService.createMember(signUpRequestDto);
		return ResponseEntity.created(URI.create("/api/member/" + memberId)).build();
	}
}
