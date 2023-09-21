package com.myblog.backend.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myblog.backend.domain.member.constant.MemberType;
import com.myblog.backend.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByEmailAndMemberType(String email, MemberType memberType);
}
