package com.colabear754.jpa_example.services.member.order

import com.colabear754.jpa_example.TestContainer
import com.colabear754.jpa_example.common.OrderStatus
import com.colabear754.jpa_example.domain.entities.item.Album
import com.colabear754.jpa_example.domain.entities.item.Book
import com.colabear754.jpa_example.domain.entities.member.Member
import com.colabear754.jpa_example.domain.entities.member.order.Order
import com.colabear754.jpa_example.domain.value.Address
import com.colabear754.jpa_example.dto.member.order.OrderItemRequest
import com.colabear754.jpa_example.repositories.item.ItemRepository
import com.colabear754.jpa_example.repositories.member.MemberRepository
import com.colabear754.jpa_example.repositories.member.order.OrderItemRepository
import com.colabear754.jpa_example.repositories.member.order.OrderRepository
import com.colabear754.jpa_example.util.TransactionHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@TestContainer
class OrderServiceTest @Autowired constructor(
    private val orderService: OrderService,
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository,
    private val orderItemRepository: OrderItemRepository,
    private val transactionHelper: TransactionHelper
) {
    @AfterEach
    fun clear() {
        orderItemRepository.deleteAll()
        itemRepository.deleteAll()
        orderRepository.deleteAll()
        memberRepository.deleteAll()
    }

    @Test
    fun 주문조회() {
        // given
        val member = memberRepository.save(Member("AAA", 20, Address("12345", "서울시 강남구"), "010-1234-5678"))
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
        val album = itemRepository.save(Album("album", 1000, 3, "artist", "etc", "createdBy", "lastModifiedBy"))
        val book = itemRepository.save(Book("book", 1000, 1, "author", "isbn", "createdBy", "lastModifiedBy"))
        // when
        orderService.newOrder(order, listOf(OrderItemRequest(album.id!!, 3), OrderItemRequest(book.id!!, 1)))
        // then
        transactionHelper.execute {
            val savedOrders = orderRepository.findAll()
            assertThat(savedOrders).hasSize(1)
            assertThat(savedOrders[0].id).isEqualTo(order.id)
            assertThat(savedOrders[0].orderItems).hasSize(2)
            assertThat(savedOrders[0].orderItems[0].item.name).isEqualTo("album")
            assertThat(savedOrders[0].orderItems[1].item.name).isEqualTo("book")
            assertThat(savedOrders[0].orderItems[0].orderPrice).isEqualTo(3000)
            assertThat(savedOrders[0].orderItems[1].orderPrice).isEqualTo(1000)
        }
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
        orderRepository.save(order)
        // when
        orderService.cancelOrder(order.id!!)
        // then
        val savedOrders = orderRepository.findAll()
        assertThat(savedOrders).hasSize(1)
        assertThat(savedOrders[0].id).isEqualTo(order.id)
        assertThat(savedOrders[0].status).isEqualTo(OrderStatus.CANCEL)
    }
}