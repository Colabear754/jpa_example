package com.colabear754.jpa_example.domain.entities.member.order.delivery

import com.colabear754.jpa_example.common.DeliveryStatus
import com.colabear754.jpa_example.domain.entities.BaseEntity
import com.colabear754.jpa_example.domain.value.Address
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
class Delivery(
    @Embedded
    val destination: Address,
    var status: DeliveryStatus,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    createdBy: String? = null,
    lastModifiedBy: String? = null
) : BaseEntity(createdBy = createdBy, lastModifiedBy = lastModifiedBy) {
    fun update(newDelivery: Delivery) {
        this.status = newDelivery.status
        this.lastModifiedBy = newDelivery.lastModifiedBy
        this.lastModifiedDate = LocalDateTime.now()
    }
}