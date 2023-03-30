package com.colabear754.jpa_example.services.member

import com.colabear754.jpa_example.entities.member.Member
import com.colabear754.jpa_example.entities.member.order.Order
import com.colabear754.jpa_example.repositories.member.MemberRepository
import com.colabear754.jpa_example.repositories.member.order.OrderRepository
import com.colabear754.jpa_example.services.order.OrderService
import com.colabear754.jpa_example.util.TransactionHelper
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.UUID

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
    private val orderRepository: OrderRepository,
    private val transactionHelper: TransactionHelper
) {
    @AfterEach
    fun clear() {
        orderRepository.deleteAll()
        memberRepository.deleteAll()
    }

    @Test
    fun 회원추가() {
        // given
        val member = Member("AAA", 20, "12345", "서울시 강남구", "010-1234-5678")
        // when
        memberService.insertMember(member)
        // then
        val savedMembers = memberRepository.findAll()
        assertThat(savedMembers).hasSize(1)
        assertThat(savedMembers[0].name).isEqualTo("AAA")
        assertThat(savedMembers[0].age).isEqualTo(20)
        assertThat(savedMembers[0].zipCode).isEqualTo("12345")
        assertThat(savedMembers[0].address).isEqualTo("서울시 강남구")
        assertThat(savedMembers[0].phoneNumber).isEqualTo("010-1234-5678")
    }

    @Test
    fun 회원수정() {
        // given
        val savedMember = memberRepository.save(Member("AAA", 20, "12345", "서울시 강남구", "010-1234-5678"))
        // when
        val updatedMember = memberService.updateMember(savedMember.id!!, Member("BBB", 30, "67890", "서울시 중구", "010-5678-1234"))
        // then
        assertThat(updatedMember.id).isEqualTo(savedMember.id!!)
        assertThat(updatedMember.name).isEqualTo("BBB")
        assertThat(updatedMember.age).isEqualTo(30)
        assertThat(updatedMember.zipCode).isEqualTo("67890")
        assertThat(updatedMember.address).isEqualTo("서울시 중구")
        assertThat(updatedMember.phoneNumber).isEqualTo("010-5678-1234")
    }

    @Test
    fun 회원삭제() {
        // given
        val savedMember = memberRepository.save(Member("AAA", 20, "12345", "서울시 강남구", "010-1234-5678"))
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
        val exception = assertThrows(NoSuchElementException::class.java) {
            memberService.updateMember(id, Member("BBB", 30, "67890", "서울시 중구", "010-5678-1234"))
        }
        // then
        assertThat(exception.message).isEqualTo("${id}에 해당하는 회원 정보를 찾을 수 없습니다.")
    }
}