package com.colabear754.jpa_example.services.member

import com.colabear754.jpa_example.entity.member.Member
import com.colabear754.jpa_example.repository.member.MemberRepository
import com.colabear754.jpa_example.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MemberService(private val memberRepository: MemberRepository) {
    @Transactional(readOnly = true)
    fun getMembers(): List<Member> = memberRepository.findAll()

    @Transactional
    fun insertMember(member: Member) = memberRepository.save(member)

    @Transactional
    fun updateMember(id: UUID, newMember: Member): Member {
        val member = memberRepository.findByIdOrThrow(id)
        member.update(newMember)
        return member
    }

    @Transactional
    fun deleteMember(id: UUID) = memberRepository.deleteById(id)
}