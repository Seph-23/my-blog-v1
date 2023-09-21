package com.myblog.backend.domain.member.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.myblog.backend.domain.common.BaseTimeEntity;
import com.myblog.backend.domain.member.constant.AccountStatus;
import com.myblog.backend.domain.member.constant.MemberType;
import com.myblog.backend.domain.member.constant.Role;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	@Column(length = 50, nullable = false)
	private String email;

	private String password;
	private String salt;

	@ColumnDefault("0")
	private Integer passwordErrorCount;

	@Column(length = 20)
	private String nickname;

	private String accessToken;
	private String refreshToken;
	private LocalDateTime tokenExpirationTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private MemberType memberType;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private AccountStatus accountStatus;

	@Builder
	public Member(Long memberId, String email, String password, String salt, Integer passwordErrorCount, String nickname, String accessToken,
		String refreshToken, LocalDateTime tokenExpirationTime, Role role, MemberType memberType, AccountStatus accountStatus) {
		this.memberId = memberId;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.passwordErrorCount = passwordErrorCount;
		this.nickname = nickname;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.tokenExpirationTime = tokenExpirationTime;
		this.role = role;
		this.memberType = memberType;
		this.accountStatus = accountStatus;
	}

	public void updateAccountStatus(AccountStatus newStatus) {
		this.accountStatus = newStatus;
	}

	public void updatePasswordErrorCount(Integer currentErrorCount) {
		this.passwordErrorCount = currentErrorCount + 1;
	}

	public void updateAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
