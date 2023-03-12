package com.colabear754.jpa_example.entity.member

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    val id: Long?,
    @Column(nullable = false)
    @Schema(description = "이름", example = "홍길동")
    var name: String,
    @Schema(description = "나이", example = "20")
    var age: Int
) {
    fun update(newMember: Member) {
        this.name = newMember.name
        this.age = newMember.age
    }
}