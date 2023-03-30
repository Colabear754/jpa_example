package com.colabear754.jpa_example.repositories.member.order

import com.colabear754.jpa_example.entities.member.Member
import com.colabear754.jpa_example.entities.member.order.Order
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrderRepository : JpaRepository<Order, UUID> {
    fun findByMember(member: Member): List<Order>
}