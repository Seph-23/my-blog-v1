package com.myblog.backend.domain.member.service;

import static com.myblog.backend.global.error.ErrorCode.*;
import static org.springframework.transaction.annotation.Propagation.*;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myblog.backend.api.login.dto.LoginRequestDto;
import com.myblog.backend.api.signup.dto.SignUpRequestDto;
import com.myblog.backend.domain.member.constant.AccountStatus;
import com.myblog.backend.domain.member.constant.MemberType;
import com.myblog.backend.domain.member.constant.Role;
import com.myblog.backend.domain.member.entity.Member;
import com.myblog.backend.domain.member.repository.MemberRepository;
import com.myblog.backend.global.error.exception.MemberException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public Long createMember(SignUpRequestDto signUpRequestDto) {
		String salt = UUID.randomUUID().toString();
		Member member = Member.builder()
			.email(signUpRequestDto.getEmail())
			.password(passwordEncoder.encode(signUpRequestDto.getPassword() + salt))
			.salt(salt)
			.nickname(signUpRequestDto.getNickname())
			.role(Role.from(signUpRequestDto.getRole()))
			.memberType(MemberType.from(signUpRequestDto.getMemberType()))
			.accountStatus(AccountStatus.ACTIVE)
			.build();
		return memberRepository.save(member).getMemberId();
	}

	@Transactional(readOnly = true)
	public Member findMemberByAccessToken(String accessToken) {
		return memberRepository.findByAccessToken(accessToken).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
	}

	@Transactional(readOnly = true)
	public void validateDuplicateMember(SignUpRequestDto signUpRequestDto) {
		memberRepository
			.findByEmailAndMemberType(signUpRequestDto.getEmail(), MemberType.from(signUpRequestDto.getMemberType()))
			.ifPresent(member -> {
				throw new MemberException(DUPLICATE_MEMBER);
			});
	}

	@Transactional(readOnly = true)
	public Member validateMemberByEmailAndMemberType(LoginRequestDto loginRequestDto) {
		return memberRepository
			.findByEmailAndMemberType(loginRequestDto.getEmail(), MemberType.from(loginRequestDto.getMemberType()))
			.orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
	}

	@Transactional(propagation = REQUIRES_NEW)
	public void updatePasswordErrorCount(Long memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
		member.updatePasswordErrorCount(member.getPasswordErrorCount());
	}

	@Transactional(propagation = REQUIRES_NEW)
	public void updateAccountStatus(Long memberId, AccountStatus newStatus) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
		member.updateAccountStatus(newStatus);
	}

	@Transactional(propagation = REQUIRES_NEW)
	public void updateAccessToken(Long memberId, String accessToken) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
		member.updateAccessToken(accessToken);
	}

	@Transactional(propagation = REQUIRES_NEW)
	public void updateRefreshToken(Long memberId, String refreshToken) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
		member.updateRefreshToken(refreshToken);
	}
}
