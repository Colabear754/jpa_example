package com.colabear754.jpa_example.dto.member.order.delivery

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

data class DeliveryRequest(
    @Schema(description = "우편 번호", example = "12345")
    val zipCode: String,
    @Schema(description = "기본 주소", example = "서울시 강남구")
    val address: String,
    @Schema(description = "상세 주소", example = "테헤란로 1234")
    val detailAddress: String,
    @Schema(description = "주문번호", example = "123e4567-e89b-12d3-a456-426614174000")
    val orderId: UUID
)