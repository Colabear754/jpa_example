package com.colabear754.jpa_example.domain.entities.item

import com.colabear754.jpa_example.common.ItemType
import com.colabear754.jpa_example.domain.entities.BaseEntity
import com.colabear754.jpa_example.domain.entities.item.category.Category
import com.colabear754.jpa_example.dto.item.ItemRequest
import com.colabear754.jpa_example.exceptions.NotEnoughStockException
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "item_type")
abstract class Item(
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var price: Long,
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
    companion object {
        fun from(request: ItemRequest) = when (request.itemType) {
            ItemType.ALBUM -> Album.from(request)
            ItemType.BOOK -> Book.from(request)
            ItemType.MOVIE -> Movie.from(request)
        }
    }

    fun addStock(quantity: Int, requestor: String) {
        stockQuantity += quantity
        lastModifiedBy = requestor
        lastModifiedDate = LocalDateTime.now()
    }

    fun removeStock(quantity: Int, requestor: String) {
        stockQuantity = (stockQuantity - quantity).also { if (it < 0) throw NotEnoughStockException("재고가 부족합니다. 현재 재고: $stockQuantity, 요청 수량: $quantity") }
        lastModifiedBy = requestor
        lastModifiedDate = LocalDateTime.now()
    }

    fun change(request: ItemRequest) {
        name = request.name
        price = request.price
        stockQuantity = request.stockQuantity
        lastModifiedBy = request.requestor
        lastModifiedDate = LocalDateTime.now()
    }

    override fun toString(): String {
        return "name='$name', price=$price, stockQuantity=$stockQuantity"
    }
}