package com.colabear754.jpa_example.dto.member.order

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

data class OrderItemRequest(
    @Schema(description = "상품 ID", example = "123e4567-e89b-12d3-a456-426614174000")
    val id: UUID,
    @Schema(description = "주문 수량", example = "1")
    val count: Int
)
