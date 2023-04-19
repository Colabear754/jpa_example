package com.colabear754.jpa_example.domain.entities.member.order

import com.colabear754.jpa_example.common.OrderStatus
import com.colabear754.jpa_example.domain.entities.BaseEntity
import com.colabear754.jpa_example.domain.entities.member.Member
import com.colabear754.jpa_example.domain.entities.member.order.delivery.Delivery
import jakarta.persistence.*
import jakarta.persistence.CascadeType.*
import jakarta.persistence.FetchType.*
import java.time.LocalDate
import java.util.*

@Entity(name = "orders")
class Order(
    @ManyToOne(fetch = LAZY)
    val member: Member?,
    @OneToMany(mappedBy = "order", cascade = [ALL])
    val orderItems: MutableList<OrderItem> = mutableListOf(),
    @OneToOne(fetch = LAZY, cascade = [ALL])
    val delivery: Delivery? = null,
    @Column(nullable = false)
    val orderDate: LocalDate = LocalDate.now(),
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ORDER,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    createdBy: String? = null,
    lastModifiedBy: String? = null
) : BaseEntity(createdBy = createdBy, lastModifiedBy = lastModifiedBy) {
    fun cancel() {
        if (status == OrderStatus.ORDER) {
            status = OrderStatus.CANCEL
        }
    }

    fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
    }
}