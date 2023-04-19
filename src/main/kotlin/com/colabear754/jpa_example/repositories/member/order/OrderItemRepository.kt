package com.colabear754.jpa_example.repositories.member.order

import com.colabear754.jpa_example.domain.entities.member.order.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrderItemRepository : JpaRepository<OrderItem, UUID>
