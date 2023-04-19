package com.colabear754.jpa_example.domain.entities.member.order.delivery

import com.colabear754.jpa_example.common.DeliveryStatus
import com.colabear754.jpa_example.domain.value.Address
import jakarta.persistence.*
import java.util.*

@Entity
class Delivery(
    @Embedded
    val address: Address,
    var status: DeliveryStatus,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
)