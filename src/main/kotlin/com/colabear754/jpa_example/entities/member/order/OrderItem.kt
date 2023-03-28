package com.colabear754.jpa_example.entities.member.order

import com.colabear754.jpa_example.entities.item.BaseItem
import jakarta.persistence.*
import java.util.*

@Entity
class OrderItem(
    @ManyToOne
    val item: BaseItem,
    @ManyToOne
    val order: Order,
    val orderPrice: Int,
    val count: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
)