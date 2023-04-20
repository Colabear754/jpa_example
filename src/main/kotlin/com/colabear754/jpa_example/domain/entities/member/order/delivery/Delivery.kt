package com.colabear754.jpa_example.domain.entities.member.order.delivery

import com.colabear754.jpa_example.common.DeliveryStatus
import com.colabear754.jpa_example.domain.entities.BaseEntity
import com.colabear754.jpa_example.domain.entities.member.order.Order
import com.colabear754.jpa_example.domain.value.Address
import com.colabear754.jpa_example.dto.member.order.delivery.DeliveryRequest
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
class Delivery(
    @Embedded
    val destination: Address,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "delivery")
    val order: Order? = null,
    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = DeliveryStatus.READY,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    createdBy: String? = null,
    lastModifiedBy: String? = null
) : BaseEntity(createdBy = createdBy, lastModifiedBy = lastModifiedBy) {
    companion object {
        fun from(request: DeliveryRequest, createdBy: String) =
            Delivery(Address(request.zipCode, request.address, request.detailAddress), createdBy = createdBy, lastModifiedBy = createdBy)
    }

    fun updateStatus(status: DeliveryStatus, lastModifiedBy: String) {
        this.status = status
        this.lastModifiedBy = lastModifiedBy
        this.lastModifiedDate = LocalDateTime.now()
    }
}