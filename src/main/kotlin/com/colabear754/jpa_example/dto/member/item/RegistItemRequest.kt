package com.colabear754.jpa_example.dto.member.item

import com.colabear754.jpa_example.common.ItemType
import io.swagger.v3.oas.annotations.media.Schema

data class RegistItemRequest(
    @Schema(description = "상품 이름", example = "상품1")
    val name: String,
    @Schema(description = "상품 가격", example = "1000")
    val price: Long,
    @Schema(description = "상품 재고", example = "10")
    val stockQuantity: Int,
    @Schema(description = "상품 타입", example = "BOOK")
    val itemType: ItemType,
    @Schema(description = "추가 정보", example = "{'key': 'value'}")
    val additionalProperties: MutableMap<String, String> = mutableMapOf(),
    @Schema(description = "상품 생성자", example = "user1")
    val createdBy: String
)