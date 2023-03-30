package com.colabear754.jpa_example.services.order

import com.colabear754.jpa_example.common.OrderStatus
import com.colabear754.jpa_example.entities.member.Member
import com.colabear754.jpa_example.entities.member.order.Order
import com.colabear754.jpa_example.repositories.member.MemberRepository
import com.colabear754.jpa_example.repositories.member.order.OrderRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderServiceTest @Autowired constructor(
    private val orderService: OrderService,
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository
) {
    @AfterEach
    fun clear() {
        orderRepository.deleteAll()
    }

    @Test
    fun 주문조회() {
        // given
        val member = memberRepository.save(Member(null, "AAA", 20, "12345", "서울시 강남구", "010-1234-5678"))
        val orders = listOf(Order(member), Order(member))
        orderService.newOrders(orders)
        // when
        val savedOrder = orderService.getOrders(member)
        // then
        assertThat(savedOrder).hasSize(2)
        assertThat(savedOrder[0].id).isEqualTo(orders[0].id)
        assertThat(savedOrder[1].id).isEqualTo(orders[1].id)
    }

    @Test
    fun 주문추가() {
        // given
        val order = Order(null)
        // when
        orderService.newOrder(order)
        // then
        val savedOrders = orderRepository.findAll()
        assertThat(savedOrders).hasSize(1)
        assertThat(savedOrders[0].id).isEqualTo(order.id)
    }

    @Test
    fun `여러 건의 주문추가`() {
        // given
        val order1 = Order(null)
        val order2 = Order(null)
        val order3 = Order(null)
        // when
        orderService.newOrders(listOf(order1, order2, order3))
        // then
        val savedOrders = orderRepository.findAll()
        assertThat(savedOrders).hasSize(3)
        assertThat(savedOrders[0].id).isEqualTo(order1.id)
        assertThat(savedOrders[1].id).isEqualTo(order2.id)
        assertThat(savedOrders[2].id).isEqualTo(order3.id)
    }

    @Test
    fun 주문취소() {
        // given
        val order = Order(null)
        orderService.newOrder(order)
        // when
        orderService.cancelOrder(order.id!!)
        // then
        val savedOrders = orderRepository.findAll()
        assertThat(savedOrders).hasSize(1)
        assertThat(savedOrders[0].id).isEqualTo(order.id)
        assertThat(savedOrders[0].status).isEqualTo(OrderStatus.CANCEL)
    }
}