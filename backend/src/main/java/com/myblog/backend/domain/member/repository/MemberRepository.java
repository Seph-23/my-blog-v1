package com.myblog.backend.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblog.backend.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
