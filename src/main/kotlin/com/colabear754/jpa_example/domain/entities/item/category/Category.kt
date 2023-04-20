package com.colabear754.jpa_example.domain.entities.item.category

import com.colabear754.jpa_example.domain.entities.BaseEntity
import com.colabear754.jpa_example.domain.entities.item.Item
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY

@Entity
class Category(
    var name: String,
    @ManyToOne(fetch = LAZY)
    var parent: Category? = null,
    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val items: MutableList<Item> = mutableListOf(),
    @OneToMany(mappedBy = "parent")
    val children: MutableList<Category> = mutableListOf(),
    createdBy: String,
    lastModifiedBy: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) : BaseEntity(createdBy = createdBy, lastModifiedBy = lastModifiedBy)