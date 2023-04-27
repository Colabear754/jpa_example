package com.colabear754.jpa_example.dto.member.order.delivery

import com.colabear754.jpa_example.common.DeliveryStatus
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

data class DeliveryStatusRequest(
    @Schema(description = "배송 번호", example = "123e4567-e89b-12d3-a456-426614174000")
    val id: UUID,
    @Schema(description = "배송 상태", example = "READY")
    val status: DeliveryStatus
)
