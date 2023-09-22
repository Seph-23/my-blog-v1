package com.myblog.backend.api.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.backend.api.member.dto.MemberInfoResponseDto;
import com.myblog.backend.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/member")
	public ResponseEntity<MemberInfoResponseDto> getMemberInfoOnLogin(HttpServletRequest httpServletRequest) {
		String accessToken = httpServletRequest.getHeader("Authorization").split(" ")[1];
		return ResponseEntity.ok(
			MemberInfoResponseDto.of(memberService.findMemberByAccessToken(accessToken))
		);
	}
}
