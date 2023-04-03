package com.colabear754.jpa_example.entities.item

import com.colabear754.jpa_example.entities.BaseEntity
import com.colabear754.jpa_example.entities.item.category.Category
import com.colabear754.jpa_example.exceptions.NotEnoughStockException
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
) : BaseEntity(createdBy = createdBy, lastModifiedBy = lastModifiedBy) {
    fun addStock(quantity: Int) {
        stockQuantity += quantity
    }

    fun removeStock(quantity: Int) {
        val restStock = stockQuantity - quantity
        if (restStock < 0) {
            throw NotEnoughStockException("재고가 부족합니다. 현재 재고: $stockQuantity, 요청 수량: $quantity")
        }
        stockQuantity = restStock
    }

    fun change(item: Item) {
        this.name = item.name
        this.price = item.price
        this.stockQuantity = item.stockQuantity
    }

    override fun toString(): String {
        return "name='$name', price=$price, stockQuantity=$stockQuantity"
    }
}