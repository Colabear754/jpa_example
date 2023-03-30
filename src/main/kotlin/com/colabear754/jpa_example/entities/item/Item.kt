package com.colabear754.jpa_example.entities.item

import com.colabear754.jpa_example.entities.BaseEntity
import com.colabear754.jpa_example.entities.item.category.Category
import jakarta.persistence.*
import java.util.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Item(
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var price: Int,
    @Column(nullable = false)
    var stockQuantity: Int = 1,
    createdBy: String,
    lastModifiedBy: String,
    @ManyToMany(mappedBy = "items")
    val categories: MutableList<Category> = mutableListOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null
) : BaseEntity(createdBy = createdBy, lastModifiedBy =  lastModifiedBy)