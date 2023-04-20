package com.colabear754.jpa_example.dto.member.order.delivery

import java.util.*

data class DeliveryRequest(
    val zipCode: String,
    val address: String,
    val detailAddress: String,
    val orderId: UUID
)