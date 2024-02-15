package com.oracle.oBootMybatis01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootMybatis01.domain.Member;
import com.oracle.oBootMybatis01.service.MemberJpaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberJpaController {
	private final MemberJpaService memberJpaService;

	@GetMapping(value = "/memberJpa/new")
	public String createForm() {
		System.out.println("MemberController /members/new start...");
		return "memberJpa/createMemberForm";
	}

	@PostMapping(value = "/memberJpa/save")
	public String create(Member member) {
		System.out.println("MemberController memberJpa/save start...");
		System.out.println("member.getId() -> " + member.getId());
		System.out.println("member.getName() -> " + member.getName());
		memberJpaService.join(member);

		return "memberJpa/createMemberForm";
	}

	@GetMapping(value = "/members")
	public String listMember(Model model) {
		System.out.println("MemberController listMember start...");
		List<Member> memberList = memberJpaService.getListAllMember();
		model.addAttribute("members", memberList);
		return "memberJpa/memberList";
	}

	@GetMapping(value = "/memberJpa/memberUpdateForm")
	// public String memberUpdateForm(Long id,Model model){
	public String memberUpdateForm(Member member1, Model model) {
		Member member = null;
		String rtnJsp = "";
		System.out.println("MemberJpaController memberUpdateForm id->" + member1.getId());
		// 목적 : 객체가 NULL Check 용이
		Optional<Member> maybeMember = memberJpaService.findById(member1.getId());
		if (maybeMember.isPresent()) {
			System.out.println("MemberJpaController memberUpdateForm maybeMember Is Not NULL");
			member = maybeMember.get();
			model.addAttribute("member", member);
			rtnJsp = "memberJpa/memberModify";
		} else {
			System.out.println("MemberJpaController memberUpdateForm maybeMember Is NULL");
			model.addAttribute("message", "member가 존재하지 않으니, 입력부터 수행해 주세요");
			rtnJsp = "forward:/members";
		}
		return rtnJsp;
	}
	
	@PostMapping(value = "/memberJpa/memberUpdate")
	public String memberUpdate(Member member,Model model) {
		System.out.println("MemberJpaController memberUpdate id ->"+member.getId());
		System.out.println("MemberJpaController memberUpdate member.getName() ->"+member.getName());
		memberJpaService.memberUpdate(member);
		return "redirect:/members";
	}
}
