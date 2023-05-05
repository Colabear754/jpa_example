package com.colabear754.jpa_example.domain.entities.item

import com.colabear754.jpa_example.dto.item.ItemRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Movie(
    name: String,
    price: Long,
    stockQuantity: Int = 1,
    @Column(nullable = false)
    var director: String,
    @Column(nullable = false)
    var actor: String,
    createdBy: String,
    lastModifiedBy: String
) : Item(name, price, stockQuantity, createdBy, lastModifiedBy) {
    override fun change(request: ItemRequest) {
        name = request.name
        price = request.price
        stockQuantity = request.stockQuantity
        director = request.additionalProperties["director"] ?: director
        actor = request.additionalProperties["actor"] ?: actor
        lastModifiedBy = request.requestor
    }

    override fun toString(): String {
        return "Movie(${super.toString()}, director='$director', actor='$actor')"
    }

    companion object {
        fun from(request: ItemRequest) = Movie(
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity,
            director = request.additionalProperties["director"] ?: "",
            actor = request.additionalProperties["actor"] ?: "",
            createdBy = request.requestor,
            lastModifiedBy = request.requestor
        )
    }
}