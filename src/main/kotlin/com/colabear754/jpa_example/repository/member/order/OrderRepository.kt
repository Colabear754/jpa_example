package com.colabear754.jpa_example.repository.member.order

import com.colabear754.jpa_example.entity.member.order.Orders
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderRepository : JpaRepository<Orders, UUID>