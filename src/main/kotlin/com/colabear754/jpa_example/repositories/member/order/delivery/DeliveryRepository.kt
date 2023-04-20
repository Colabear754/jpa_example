package com.colabear754.jpa_example.repositories.member.order.delivery

import com.colabear754.jpa_example.domain.entities.member.order.delivery.Delivery
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DeliveryRepository : JpaRepository<Delivery, UUID>