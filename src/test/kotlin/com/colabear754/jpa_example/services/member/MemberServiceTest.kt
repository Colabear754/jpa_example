package com.colabear754.jpa_example.services.member

import com.colabear754.jpa_example.entity.member.Member
import com.colabear754.jpa_example.entity.member.MemberRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) {
    @AfterEach
    fun clear() {
        memberRepository.deleteAll()
    }

    @Test
    fun 회원추가() {
        // given
        val member = Member(null, "AAA", 20)
        // when
        memberService.insertMember(member)
        // then
        val savedMembers = memberRepository.findAll()
        assertThat(savedMembers).hasSize(1)
        assertThat(savedMembers[0].username).isEqualTo("AAA")
        assertThat(savedMembers[0].age).isEqualTo(20)
    }

    @Test
    fun 회원수정() {
        // given
        val savedMember = memberRepository.save(Member(null, "AAA", 20))
        // when
        val updatedMember = memberService.updateMember(savedMember.id!!, Member(null, "BBB", 30))
        // then
        assertThat(updatedMember.id).isEqualTo(1L)
        assertThat(updatedMember.username).isEqualTo("BBB")
        assertThat(updatedMember.age).isEqualTo(30)
    }

    @Test
    fun 회원삭제() {
        // given
        val savedMember = memberRepository.save(Member(null, "AAA", 20))
        // when
        memberService.deleteMember(savedMember.id!!)
        // then
        val savedMembers = memberRepository.findAll()
        assertThat(savedMembers).hasSize(0)
    }
}