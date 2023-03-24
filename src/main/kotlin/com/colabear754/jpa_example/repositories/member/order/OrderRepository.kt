package com.colabear754.jpa_example.repositories.member.order

import com.colabear754.jpa_example.entities.member.order.Order
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderRepository : JpaRepository<Order, UUID> {
    fun findByMemberId(id: UUID): List<Order>
}