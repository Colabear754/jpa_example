package com.colabear754.jpa_example.domain.entities.member.order

import com.colabear754.jpa_example.domain.entities.item.Item
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import java.util.*

@Entity
class OrderItem(
    @ManyToOne(fetch = LAZY)
    val item: Item,
    @ManyToOne(fetch = LAZY)
    val order: Order,
    val count: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
) {
    val orderPrice: Long get() = item.price * count
}