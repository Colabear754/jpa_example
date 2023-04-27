package com.colabear754.jpa_example.dto.member

import com.colabear754.jpa_example.domain.entities.member.Member
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

data class MemberResponse(
    @Schema(hidden = true)
    val id: UUID,
    @Schema(description = "이름", example = "홍길동")
    var name: String,
    @Schema(description = "나이", example = "20")
    var age: Int?,
    @Schema(description = "우편번호", example = "12345")
    var zipCode: String,
    @Schema(description = "주소", example = "서울시 강남구")
    var address: String,
    @Schema(description = "상세 주소", example = "상세 주소")
    var detailAddress: String,
    @Schema(description = "전화번호", example = "010-1234-5678")
    var phoneNumber: String
) {
    companion object {
        fun from(member: Member) = MemberResponse(
            id = member.id!!,
            name = member.name,
            age = member.age,
            zipCode = member.address.zipCode,
            address = member.address.address,
            detailAddress = member.address.detailAddress,
            phoneNumber = member.phoneNumber
        )
    }
}