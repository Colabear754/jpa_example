package com.colabear754.jpa_example.services.member

import com.colabear754.jpa_example.entity.member.Member
import com.colabear754.jpa_example.entity.member.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {
    fun insertMember(member: Member): Member {
        memberRepository.save(member)
        return memberRepository.findByUsername(member.username) ?: throw NoSuchElementException("Member not found")
    }
}