package com.colabear754.jpa_example.services.member.order

import com.colabear754.jpa_example.domain.entities.member.Member
import com.colabear754.jpa_example.domain.entities.member.order.Order
import com.colabear754.jpa_example.domain.entities.member.order.OrderItem
import com.colabear754.jpa_example.dto.member.order.OrderItemRequest
import com.colabear754.jpa_example.exceptions.NotEnoughStockException
import com.colabear754.jpa_example.repositories.item.ItemRepository
import com.colabear754.jpa_example.repositories.member.order.OrderItemRepository
import com.colabear754.jpa_example.repositories.member.order.OrderRepository
import com.colabear754.jpa_example.util.findByIdOrThrow
import com.colabear754.jpa_example.util.logger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository, private val orderItemRepository: OrderItemRepository,
    private val itemRepository: ItemRepository
) {
    val log = logger()

    @Transactional(readOnly = true)
    fun getOrders(member: Member): List<Order> = orderRepository.findByMember(member)

    @Transactional
    fun newOrder(order: Order, orderItems: List<OrderItemRequest>): Order {
        orderRepository.save(order)
        for (dto in orderItems) {
            val item = itemRepository.findByIdOrThrow(dto.id, "존재하지 않는 상품입니다.")
            try {
                item.removeStock(dto.count)
                val orderItem = OrderItem(item, order, dto.count)
                orderItemRepository.save(orderItem)
                order.addOrderItem(orderItem)
            } catch (e: NotEnoughStockException) {
                log.error("재고가 부족하여 ${item.name}의 주문을 취소합니다.")
                continue
            }
        }
        return order
    }

    @Transactional
    fun newOrders(orders: List<Order>): List<Order> = orderRepository.saveAll(orders)

    @Transactional
    fun cancelOrder(id: UUID) {
        val order = orderRepository.findByIdOrThrow(id, "존재하지 않는 주문번호입니다.")
        order.cancel()
    }
}