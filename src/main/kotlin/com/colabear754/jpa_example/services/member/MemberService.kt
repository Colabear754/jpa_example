package com.colabear754.jpa_example.services.member

import com.colabear754.jpa_example.domain.entities.member.Member
import com.colabear754.jpa_example.dto.member.MemberRequest
import com.colabear754.jpa_example.dto.member.MemberResponse
import com.colabear754.jpa_example.repositories.member.MemberRepository
import com.colabear754.jpa_example.util.badRequest
import com.colabear754.jpa_example.util.findByIdOrThrow
import com.colabear754.jpa_example.util.flushOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MemberService(private val memberRepository: MemberRepository) {
    @Transactional(readOnly = true)
    fun getMember(id: UUID): Member = memberRepository.findByIdOrThrow(id, "존재하지 않는 회원입니다.")

    @Transactional(readOnly = true)
    fun getMembers(): List<Member> = memberRepository.findAll()

    @Transactional
    fun insertMember(request: MemberRequest): MemberResponse {
        val member = memberRepository.flushOrThrow(badRequest("이미 등록된 전화번호입니다.")) { save(Member.of(request)) }
        return MemberResponse.from(member)
    }

    @Transactional
    fun updateMember(id: UUID, request: MemberRequest): MemberResponse {
        val member = memberRepository.findByIdOrThrow(id, "존재하지 않는 회원입니다.")
        memberRepository.flushOrThrow(badRequest("이미 등록된 전화번호입니다.")) { member.update(Member.of(request)) }
        return MemberResponse.from(member)
    }

    @Transactional
    fun deleteMember(id: UUID) = memberRepository.deleteById(id)
}