package com.myblog.backend.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myblog.backend.api.signup.dto.SignUpRequestDto;
import com.myblog.backend.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void memberSignUp(SignUpRequestDto signUpRequestDto) {

	}
}
