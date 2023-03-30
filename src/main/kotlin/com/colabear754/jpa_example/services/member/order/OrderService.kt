package com.colabear754.jpa_example.services.member.order

import com.colabear754.jpa_example.entities.member.Member
import com.colabear754.jpa_example.entities.member.order.Order
import com.colabear754.jpa_example.repositories.member.order.OrderRepository
import com.colabear754.jpa_example.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class OrderService(private val orderRepository: OrderRepository) {
    @Transactional(readOnly = true)
    fun getOrders(member: Member): List<Order> = orderRepository.findByMember(member)

    @Transactional
    fun newOrder(order: Order) = orderRepository.save(order)

    @Transactional
    fun newOrders(orders: List<Order>): List<Order> = orderRepository.saveAll(orders)

    @Transactional
    fun cancelOrder(id: UUID) {
        val order = orderRepository.findByIdOrThrow(id, "존재하지 않는 주문번호입니다.")
        order.cancel()
    }
}