package com.myblog.backend.api.member.dto;

import com.myblog.backend.domain.member.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoResponseDto {

	private String email;
	private String nickname;

	public static MemberInfoResponseDto of(Member member) {
		return MemberInfoResponseDto.builder()
			.email(member.getEmail())
			.nickname(member.getNickname())
			.build();
	}
}
