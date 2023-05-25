package com.colabear754.jpa_example.services.member

import com.colabear754.jpa_example.TestContainer
import com.colabear754.jpa_example.domain.entities.member.Member
import com.colabear754.jpa_example.domain.value.Address
import com.colabear754.jpa_example.dto.member.MemberRequest
import com.colabear754.jpa_example.repositories.member.MemberRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@TestContainer
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) {
    @AfterEach
    fun clear() {
        memberRepository.deleteAllInBatch()
    }

    @Test
    fun 회원추가() {
        // given
        val request = MemberRequest("AAA", 20, "12345", "서울시 강남구", "상세 주소", "010-1234-5678", "requestor")
        // when
        memberService.insertMember(request)
        // then
        val savedMembers = memberRepository.findAll()
        assertThat(savedMembers).hasSize(1)
        assertThat(savedMembers[0].name).isEqualTo("AAA")
        assertThat(savedMembers[0].age).isEqualTo(20)
        assertThat(savedMembers[0].address).isEqualTo(Address("12345", "서울시 강남구", "상세 주소"))
        assertThat(savedMembers[0].phoneNumber).isEqualTo("010-1234-5678")
    }

    @Test
    fun `회원의 전화번호는 중복될 수 없다`() {
        // given
        val request1 = MemberRequest("AAA", 20, "12345", "서울시 강남구", "상세 주소", "010-1234-5678", "requestor")
        memberService.insertMember(request1)
        // when
        val request2 = MemberRequest("BBB", 30, "67890", "서울시 중구", "새 상세 주소", "010-1234-5678", "requestor")
        // then
        assertThrows<IllegalArgumentException> {
            memberService.insertMember(request2)
        }.also {
            assertThat(it.message).isEqualTo("이미 등록된 전화번호입니다.")
        }
    }

    @Test
    fun 회원수정() {
        // given
        val savedMember =
            memberRepository.save(Member("AAA", 20, Address("12345", "서울시 강남구", "상세 주소"), "010-1234-5678"))
        // when
        val updatedMember = memberService.updateMember(savedMember.id!!, MemberRequest("BBB", 30, "67890", "서울시 중구", "새 상세 주소", "010-5678-1234", "requestor"))
        // then
        assertThat(updatedMember.id).isEqualTo(savedMember.id!!)
        assertThat(updatedMember.name).isEqualTo("BBB")
        assertThat(updatedMember.age).isEqualTo(30)
        assertThat(updatedMember.zipCode).isEqualTo("67890")
        assertThat(updatedMember.address).isEqualTo("서울시 중구")
        assertThat(updatedMember.detailAddress).isEqualTo("새 상세 주소")
        assertThat(updatedMember.phoneNumber).isEqualTo("010-5678-1234")
    }

    @Test
    fun 회원삭제() {
        // given
        val savedMember =
            memberRepository.save(Member("AAA", 20, Address("12345", "서울시 강남구", "상세 주소"), "010-1234-5678"))
        // when
        memberService.deleteMember(savedMember.id!!)
        // then
        val savedMembers = memberRepository.findAll()
        assertThat(savedMembers).hasSize(0)
    }

    @Test
    fun `존재하지 않는 회원을 수정하려고 하면 예외가 발생한다`() {
        // given
        val id = UUID.randomUUID()
        // when
        val exception = assertThrows<NoSuchElementException> {
            memberService.updateMember(id, MemberRequest("BBB", 30, "67890", "서울시 중구", "새 상세 주소", "010-5678-1234", "requestor"))
        }
        // then
        assertThat(exception.message).isEqualTo("존재하지 않는 회원입니다.")
    }
}