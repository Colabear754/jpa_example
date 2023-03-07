package com.colabear754.jpa_example.services.member

import com.colabear754.jpa_example.entity.member.Member
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService
) {
    @Test
    fun 회원추가() {
        // given
        val member = Member(null, "AAA", 20)

        // when
        val savedMember = memberService.insertMember(member)

        // then
        assertThat(savedMember.id).isEqualTo(1L)
        assertThat(savedMember.username).isEqualTo("AAA")
        assertThat(savedMember.age).isEqualTo(20)
    }
}