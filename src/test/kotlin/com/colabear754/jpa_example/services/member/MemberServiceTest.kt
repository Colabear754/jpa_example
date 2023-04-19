package com.colabear754.jpa_example.services.member

import com.colabear754.jpa_example.TestContainer
import com.colabear754.jpa_example.domain.entities.member.Member
import com.colabear754.jpa_example.domain.value.Address
import com.colabear754.jpa_example.repositories.member.MemberRepository
import com.colabear754.jpa_example.repositories.member.order.OrderRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@TestContainer
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
    private val orderRepository: OrderRepository
) {
    @AfterEach
    fun clear() {
        orderRepository.deleteAll()
        memberRepository.deleteAll()
    }

    @Test
    fun 회원추가() {
        // given
        val address = Address("12345", "서울시 강남구")
        val member = Member("AAA", 20, address, "010-1234-5678")
        // when
        memberService.insertMember(member)
        // then
        val savedMembers = memberRepository.findAll()
        assertThat(savedMembers).hasSize(1)
        assertThat(savedMembers[0].name).isEqualTo("AAA")
        assertThat(savedMembers[0].age).isEqualTo(20)
        assertThat(savedMembers[0].address).isEqualTo(address)
        assertThat(savedMembers[0].phoneNumber).isEqualTo("010-1234-5678")
    }

    @Test
    fun 회원수정() {
        // given
        val savedMember = memberRepository.save(Member("AAA", 20, Address("12345", "서울시 강남구"), "010-1234-5678"))
        // when
        val newAddress = Address("67890", "서울시 중구")
        val updatedMember = memberService.updateMember(savedMember.id!!, Member("BBB", 30, newAddress, "010-5678-1234"))
        // then
        assertThat(updatedMember.id).isEqualTo(savedMember.id!!)
        assertThat(updatedMember.name).isEqualTo("BBB")
        assertThat(updatedMember.age).isEqualTo(30)
        assertThat(updatedMember.address).isEqualTo(newAddress)
        assertThat(updatedMember.phoneNumber).isEqualTo("010-5678-1234")
    }

    @Test
    fun 회원삭제() {
        // given
        val savedMember = memberRepository.save(Member("AAA", 20, Address("12345", "서울시 강남구"), "010-1234-5678"))
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
            memberService.updateMember(id, Member("BBB", 30, Address("67890", "서울시 중구"), "010-5678-1234"))
        }
        // then
        assertThat(exception.message).isEqualTo("존재하지 않는 회원입니다.")
    }
}