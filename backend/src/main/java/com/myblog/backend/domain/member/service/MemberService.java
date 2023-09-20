package com.myblog.backend.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myblog.backend.api.signup.dto.SignUpRequestDto;
import com.myblog.backend.domain.member.constant.MemberType;
import com.myblog.backend.domain.member.constant.Role;
import com.myblog.backend.domain.member.entity.Member;
import com.myblog.backend.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public void createMember(SignUpRequestDto signUpRequestDto) {
		String salt = "%$#@!^&*()1324";
		Member member = Member.builder()
			.email(signUpRequestDto.getEmail())
			.password(passwordEncoder.encode(signUpRequestDto.getPassword() + salt))
			.nickname(signUpRequestDto.getNickname())
			.role(Role.from(signUpRequestDto.getRole()))
			.memberType(MemberType.from(signUpRequestDto.getMemberType()))
			.build();
		memberRepository.save(member);
	}

	public void validateDuplicateMember(SignUpRequestDto signUpRequestDto) {
		memberRepository
			.findByEmailAndMemberType(signUpRequestDto.getEmail(), MemberType.from(signUpRequestDto.getMemberType()))
			.ifPresent(member -> {
				throw new RuntimeException("이미 존재하는 회원입니다.");
			});
	}
}
