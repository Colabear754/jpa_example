package com.colabear754.jpa_example.entities.item.category

import com.colabear754.jpa_example.entities.item.Item
import jakarta.persistence.*

@Entity
class Category(
    var name: String,
    @ManyToOne
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)