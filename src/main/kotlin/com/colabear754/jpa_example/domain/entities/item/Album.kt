package com.colabear754.jpa_example.domain.entities.item

import com.colabear754.jpa_example.dto.item.ItemRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Album(
    name: String,
    price: Long,
    stockQuantity: Int = 1,
    @Column(nullable = false)
    var artist: String,
    @Column(nullable = false)
    var etc: String,
    createdBy: String,
    lastModifiedBy: String
) : Item(name, price, stockQuantity, createdBy, lastModifiedBy) {
    override fun change(request: ItemRequest) {
        artist = request.additionalProperties["artist"] ?: artist
        etc = request.additionalProperties["etc"] ?: etc
        super.change(request)
    }

    override fun toString(): String {
        return "Album(${super.toString()}, artist='$artist', etc='$etc')"
    }

    companion object {
        fun from(request: ItemRequest) = Album(
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity,
            artist = request.additionalProperties["artist"] ?: "",
            etc = request.additionalProperties["etc"] ?: "",
            createdBy = request.requestor,
            lastModifiedBy = request.requestor
        )
    }
}