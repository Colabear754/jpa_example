package com.colabear754.jpa_example.entities.member.order.delivery

import com.colabear754.jpa_example.common.DeliveryStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
class Delivery(
    val city: String,
    val street: String,
    val zipcode: String,
    var status: DeliveryStatus,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
)