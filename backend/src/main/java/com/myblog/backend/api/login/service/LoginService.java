package com.myblog.backend.api.login.service;

import static com.myblog.backend.global.error.ErrorCode.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myblog.backend.api.login.dto.LoginRequestDto;
import com.myblog.backend.api.login.dto.LoginResponseDto;
import com.myblog.backend.domain.member.constant.AccountStatus;
import com.myblog.backend.domain.member.entity.Member;
import com.myblog.backend.domain.member.service.MemberService;
import com.myblog.backend.global.error.exception.AuthenticationException;
import com.myblog.backend.global.jwt.dto.JwtTokenDto;
import com.myblog.backend.global.jwt.service.TokenManager;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

	private final TokenManager tokenManager;
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		Member member = memberService.validateMemberByEmailAndMemberType(loginRequestDto);
		checkAccountStatus(member);
		checkPasswordErrorCount(member);
		checkPassword(loginRequestDto, member);
		JwtTokenDto jwtTokenDto = tokenManager.createJwtTokenDto(member);
		memberService.updateAccessToken(member.getMemberId(), jwtTokenDto.getAccessToken());
		memberService.updateRefreshToken(member.getMemberId(), jwtTokenDto.getRefreshToken());
		return LoginResponseDto.of(jwtTokenDto);
	}

	private void checkAccountStatus(Member member) {
		if (member.getAccountStatus() == AccountStatus.BLOCKED) {
			throw new AuthenticationException(ACCOUNT_BLOCKED);
		}
	}

	private void checkPasswordErrorCount(Member member) {
		if (member.getPasswordErrorCount() > 4) {
			memberService.updateAccountStatus(member.getMemberId(), AccountStatus.BLOCKED);
			throw new AuthenticationException(PASSWORD_ERROR_COUNT_EXCEEDED);
		}
	}

	private void checkPassword(LoginRequestDto loginRequestDto, Member member) {
		String requestedPW = loginRequestDto.getPassword() + member.getSalt();
		if (!passwordEncoder.matches(requestedPW, member.getPassword())) {
			memberService.updatePasswordErrorCount(member.getMemberId());
			throw new AuthenticationException(INVALID_PASSWORD);
		}
	}
}
