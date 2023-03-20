package com.colabear754.jpa_example.entities.item

import jakarta.persistence.*
import java.util.*

@Entity
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var price: Int,
    @Column(nullable = false)
    var stockQuantity: Int = 1,
)