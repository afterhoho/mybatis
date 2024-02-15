package com.oracle.oBootMybatis01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepositoryImpl implements MemberJpaRepository {

	private final EntityManager em;
	
	@Override
	public Member save(Member member) {
		System.out.println("MemberJpaRepositoryImpl save start...");
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		System.out.println("MemberJpaRepositoryImpl findAll start...");
		List<Member>memberList = em.createQuery("select m from Member m",Member.class)
									.getResultList();
		return memberList;
	}

	@Override
	public Optional<Member> findById(Long memberId) {
		Member member = em.find(Member.class, memberId);
		return Optional.ofNullable(member);
	}

	@Override
	public void updateByMember(Member member) {
		System.out.println("MemberJpaRepositoryImpl updateByMember member ->" + member);
		// merge --> 현재 setting 된것만 수정, 나머지는 null
		// em.merge(member);
		Member member3 =em.find(Member.class, member.getId());
		member3.setId(member.getId());
		member3.setName(member.getName());
		
		//em.merge(member);
		
	}

}
