package com.myblog.backend.domain.member.constant;

import java.util.Arrays;

public enum MemberType {

	BLOG, KAKAO, GOOGLE;

	public static MemberType from(String type) {
		return MemberType.valueOf(type.toUpperCase());
	}

	public static boolean isMemberType(String type) {
		return Arrays.stream(MemberType.values())
			.anyMatch(memberType -> memberType.name().equals(type));
	}
}
