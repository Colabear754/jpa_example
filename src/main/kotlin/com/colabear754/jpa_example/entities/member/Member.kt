package com.colabear754.jpa_example.entities.member

import com.colabear754.jpa_example.entities.member.order.Orders
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.util.UUID

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden = true)
    val id: UUID?,
    @Schema(description = "이름", example = "홍길동")
    @Column(nullable = false)
    var name: String,
    @Schema(description = "나이", example = "20")
    var age: Int?,
    @Schema(description = "우편번호", example = "12345")
    @Column(nullable = false)
    var zipCode: String,
    @Schema(description = "주소", example = "서울시 강남구")
    @Column(nullable = false)
    var address: String?,
    @Schema(description = "전화번호", example = "010-1234-5678")
    @Column(nullable = false)
    var phoneNumber: String
) {
    fun update(newMember: Member) {
        this.name = newMember.name
        this.age = newMember.age
        this.zipCode = newMember.zipCode
        this.address = newMember.address
        this.phoneNumber = newMember.phoneNumber
    }
}