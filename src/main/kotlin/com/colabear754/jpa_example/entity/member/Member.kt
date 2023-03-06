package com.colabear754.jpa_example.entity.member

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    val id: Long?,
    @Schema(description = "이름", example = "홍길동")
    val username: String,
    @Schema(description = "나이", example = "20")
    val age: Int
)