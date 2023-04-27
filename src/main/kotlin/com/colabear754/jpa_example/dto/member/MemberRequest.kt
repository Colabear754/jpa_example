package com.colabear754.jpa_example.dto.member

import io.swagger.v3.oas.annotations.media.Schema

data class MemberRequest(
    @Schema(description = "이름", example = "홍길동")
    val name: String,
    @Schema(description = "나이", example = "20")
    val age: Int?,
    @Schema(description = "우편번호", example = "12345")
    val zipCode: String,
    @Schema(description = "주소", example = "서울시 강남구")
    val address: String,
    @Schema(description = "상세 주소", example = "상세 주소")
    val detailAddress: String,
    @Schema(description = "전화번호", example = "010-1234-5678")
    val phoneNumber: String,
    @Schema(description = "요청자", example = "requestor")
    val requestor: String
)