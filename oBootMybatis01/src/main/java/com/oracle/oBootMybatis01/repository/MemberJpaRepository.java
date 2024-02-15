package com.oracle.oBootMybatis01.repository;

import java.util.List;
import java.util.Optional;

import com.oracle.oBootMybatis01.domain.Member;

public interface MemberJpaRepository {

	Member		save(Member member);
	List<Member>findAll();
	Optional<Member> findById(Long memberId);
	void updateByMember(Member member);
}
