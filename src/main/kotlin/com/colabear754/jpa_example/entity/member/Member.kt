package com.colabear754.jpa_example.entity.member

import com.colabear754.jpa_example.common.UserRole
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.time.LocalDate
import java.util.UUID

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden = true)
    val id: UUID?,
    @Column(nullable = false)
    @Schema(description = "이름", example = "홍길동")
    var name: String,
    @Schema(description = "나이", example = "20")
    var age: Int?,
    @Schema(description = "권한", example = "USER")
    @Enumerated(EnumType.STRING)
    var role: UserRole,
    @Schema(description = "비고", example = "비고")
    var remark: String?,
    @Schema(hidden = true)
    val createDate: LocalDate = LocalDate.now(),
    @Schema(hidden = true)
    var updateDate: LocalDate = LocalDate.now()
) {
    fun update(newMember: Member) {
        this.name = newMember.name
        this.age = newMember.age
        this.role = newMember.role
        this.remark = newMember.remark
        this.updateDate = LocalDate.now()
    }
}