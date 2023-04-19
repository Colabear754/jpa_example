package com.colabear754.jpa_example.domain.entities.member

import com.colabear754.jpa_example.domain.entities.BaseEntity
import com.colabear754.jpa_example.domain.value.Address
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.util.*

@Entity
class Member(
    @Column(nullable = false)
    var name: String,
    var age: Int?,
    @Column(nullable = false)
    @Embedded
    var address: Address,
    @Column(nullable = false)
    var phoneNumber: String,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden = true)
    val id: UUID? = null,
    createdBy: String? = null,
    lastModifiedBy: String? = null
) : BaseEntity(createdBy = createdBy, lastModifiedBy = lastModifiedBy) {
    fun update(newMember: Member) {
        this.name = newMember.name
        this.age = newMember.age
        this.address = newMember.address
        this.phoneNumber = newMember.phoneNumber
    }
}