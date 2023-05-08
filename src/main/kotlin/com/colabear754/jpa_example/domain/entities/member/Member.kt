package com.colabear754.jpa_example.domain.entities.member

import com.colabear754.jpa_example.domain.entities.BaseEntity
import com.colabear754.jpa_example.domain.value.Address
import com.colabear754.jpa_example.dto.member.MemberRequest
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
class Member(
    @Column(nullable = false)
    var name: String,
    var age: Int?,
    @Column(nullable = false)
    @Embedded
    var address: Address,
    @Column(nullable = false, unique = true)
    var phoneNumber: String,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    createdBy: String? = null,
    lastModifiedBy: String? = null
) : BaseEntity(createdBy = createdBy, lastModifiedBy = lastModifiedBy) {
    companion object {
        fun from(request: MemberRequest): Member {
            return Member(
                name = request.name,
                age = request.age,
                address = Address(
                    zipCode = request.zipCode,
                    address = request.address,
                    detailAddress = request.detailAddress),
                phoneNumber = request.phoneNumber,
                createdBy = request.requestor,
                lastModifiedBy = request.requestor
            )
        }
    }

    fun update(newMember: Member) {
        this.name = newMember.name
        this.age = newMember.age
        this.address = newMember.address
        this.phoneNumber = newMember.phoneNumber
        this.lastModifiedBy = newMember.lastModifiedBy
        this.lastModifiedDate = LocalDateTime.now()
    }
}