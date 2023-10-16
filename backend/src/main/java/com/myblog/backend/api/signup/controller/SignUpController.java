package com.myblog.backend.api.signup.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.backend.api.signup.dto.SignUpRequestDto;
import com.myblog.backend.domain.member.service.MemberService;
import com.myblog.backend.global.common.controller.AbstractController;
import com.myblog.backend.global.common.dto.BusinessResponseDto;
import com.myblog.backend.global.common.dto.CreatedResponseBodyDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignUpController extends AbstractController {

	private final MemberService memberService;

	@PostMapping("/signup")
	public BusinessResponseDto<CreatedResponseBodyDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto,
		HttpServletResponse httpServletResponse) {
		Long memberId = memberService.createMember(signUpRequestDto);
		String location = "/api/member/" + memberId;
		httpServletResponse.setHeader("location", location);
		return created(CreatedResponseBodyDto.builder().location(location).build());
	}
}
