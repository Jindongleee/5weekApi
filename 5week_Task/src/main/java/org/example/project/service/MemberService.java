package org.example.project.service;

import jakarta.transaction.Transactional;
import org.example.project.Repository.MemberRepository;
import org.example.project.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Transactional
    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

}