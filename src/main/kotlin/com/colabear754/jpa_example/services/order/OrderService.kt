package com.colabear754.jpa_example.services.order

import com.colabear754.jpa_example.entities.member.order.Orders
import com.colabear754.jpa_example.repositories.member.order.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(private val orderRepository: OrderRepository) {
    @Transactional
    fun newOrder(order: Orders) = orderRepository.save(order)

    @Transactional
    fun newOrders(orders: List<Orders>): List<Orders> = orderRepository.saveAll(orders)
}