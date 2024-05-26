package org.example.project.service;

import jakarta.transaction.Transactional;
import org.example.project.Repository.MemberRepository;
import org.example.project.domain.Member;
import org.example.project.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional //readOnly=true 잠재적 문제 방지용
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

    @Transactional
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    @Transactional
    public Member updateMember(Long id, MemberDto memberDto){
        Optional<Member> memberOptional = memberRepository.findById(id);
        if(memberOptional.isPresent()){
            Member member = memberOptional.get();
            if(memberDto.getName() != null){
                member.setName(memberDto.getName());
            }
            if(memberDto.getEmail()!=null){
                member.setEmail(memberDto.getEmail());
            }
            return memberRepository.save(member);
        }
        else{
            throw new RuntimeException("Member not found with id "+id);
        }
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }




}