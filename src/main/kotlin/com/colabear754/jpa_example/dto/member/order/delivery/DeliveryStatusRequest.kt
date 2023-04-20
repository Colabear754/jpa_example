package com.colabear754.jpa_example.dto.member.order.delivery

import com.colabear754.jpa_example.common.DeliveryStatus
import java.util.*

data class DeliveryStatusRequest(
    val id: UUID,
    val status: DeliveryStatus
)
