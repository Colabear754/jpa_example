package com.colabear754.jpa_example.domain.entities.item

import com.colabear754.jpa_example.dto.item.ItemRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDateTime

@Entity
class Book(
    name: String,
    price: Long,
    stockQuantity: Int = 1,
    @Column(nullable = false)
    var author: String,
    @Column(nullable = false)
    var isbn: String,
    createdBy: String,
    lastModifiedBy: String,
) : Item(name, price, stockQuantity, createdBy, lastModifiedBy) {
    override fun change(request: ItemRequest) {
        name = request.name
        price = request.price
        stockQuantity = request.stockQuantity
        author = request.additionalProperties["author"] ?: author
        isbn = request.additionalProperties["isbn"] ?: isbn
        lastModifiedBy = request.requestor
        lastModifiedDate = LocalDateTime.now()
    }

    override fun toString(): String {
        return "Book(${super.toString()}, author='$author', isbn='$isbn')"
    }

    companion object {
        fun from(request: ItemRequest) = Book(
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity,
            author = request.additionalProperties["author"] ?: "",
            isbn = request.additionalProperties["isbn"] ?: "",
            createdBy = request.requestor,
            lastModifiedBy = request.requestor
        )
    }
}