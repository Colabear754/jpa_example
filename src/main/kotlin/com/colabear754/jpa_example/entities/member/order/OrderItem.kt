package com.colabear754.jpa_example.entities.member.order

import com.colabear754.jpa_example.entities.item.Item
import jakarta.persistence.*
import java.util.*

@Entity
class OrderItem(
    @ManyToOne
    val item: Item,
    @ManyToOne
    val order: Order,
    val count: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
) {
    val orderPrice: Int get() = item.price * count
}