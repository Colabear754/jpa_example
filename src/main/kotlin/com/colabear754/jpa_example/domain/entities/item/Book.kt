package com.colabear754.jpa_example.domain.entities.item

import com.colabear754.jpa_example.dto.member.item.RegistItemRequest
import com.colabear754.jpa_example.util.typeMismatch
import jakarta.persistence.Entity

@Entity
class Book(
    name: String,
    price: Int,
    stockQuantity: Int = 1,
    var author: String,
    var isbn: String,
    createdBy: String,
    lastModifiedBy: String,
) : Item(name, price, stockQuantity, createdBy, lastModifiedBy) {
    override fun change(item: Item) {
        if (item is Book) {
            this.author = item.author
            this.isbn = item.isbn
            super.change(item)
        } else {
            typeMismatch("입력된 상품 정보가 책이 아닙니다.")
        }
    }

    override fun toString(): String {
        return "Book(${super.toString()}, author='$author', isbn='$isbn')"
    }

    companion object {
        fun from(request: RegistItemRequest) = Book(
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity,
            author = request.additionalProperties["author"] ?: "",
            isbn = request.additionalProperties["isbn"] ?: "",
            createdBy = request.createdBy,
            lastModifiedBy = request.createdBy
        )
    }
}