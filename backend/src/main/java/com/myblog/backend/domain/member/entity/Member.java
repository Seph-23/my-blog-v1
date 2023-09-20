package com.myblog.backend.domain.member.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;

import com.myblog.backend.domain.common.BaseTimeEntity;
import com.myblog.backend.domain.member.constant.MemberType;
import com.myblog.backend.domain.member.constant.Role;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	@Column(length = 50, nullable = false)
	private String email;

	private String password;

	@Column(length = 20)
	private String nickname;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private MemberType memberType;

	private String refreshToken;

	private LocalDateTime tokenExpirationTime;

	@Builder
	public Member(Long memberId, String email, String password, String nickname, Role role, MemberType memberType,
		String refreshToken, LocalDateTime tokenExpirationTime) {
		this.memberId = memberId;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.role = role;
		this.memberType = memberType;
		this.refreshToken = refreshToken;
		this.tokenExpirationTime = tokenExpirationTime;
	}
}
