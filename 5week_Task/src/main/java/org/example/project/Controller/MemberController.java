package org.example.project.Controller;

import org.example.project.domain.Member;
import org.example.project.dto.MemberDto;
import org.example.project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDto> registerMember(@RequestBody MemberDto memberDto){
        Member member = new Member();
        member.setName(member.getName());
        member.setEmail(member.getEmail());

        Member registeredMember = memberService.registerMember(member);
        return ResponseEntity.ok(MemberDto.from(registeredMember));
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAllMembers(){
        List<Member> members = memberService.getAllMembers();
        List<MemberDto> memberDtos = new ArrayList<>();
        for(Member member:members){
            MemberDto dto = MemberDto.from(member);
            memberDtos.add(dto);
        }
        return ResponseEntity.ok(memberDtos);
    }

    //{id}로 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable(name="id") Long id){
        Optional<Member> memberOptional = memberService.getMemberById(id);
        if(memberOptional.isPresent()){
            MemberDto dto = MemberDto.from(memberOptional.get());
            return ResponseEntity.ok(dto);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
