package com.myblog.backend.api.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.backend.api.member.dto.MemberInfoResponseDto;
import com.myblog.backend.domain.member.service.MemberService;
import com.myblog.backend.global.common.controller.AbstractController;
import com.myblog.backend.global.common.dto.BusinessResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController extends AbstractController {

	private final MemberService memberService;

	@GetMapping("/member")
	public BusinessResponseDto<MemberInfoResponseDto> getMemberInfoOnLogin(HttpServletRequest httpServletRequest) {
		return ok(MemberInfoResponseDto.of(memberService.findMemberByAccessToken(httpServletRequest)));
	}
}
