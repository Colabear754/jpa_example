package com.colabear754.jpa_example.domain.entities.item

import com.colabear754.jpa_example.dto.member.item.RegistItemRequest
import com.colabear754.jpa_example.util.typeMismatch
import jakarta.persistence.Entity

@Entity
class Movie(
    name: String,
    price: Long,
    stockQuantity: Int = 1,
    var director: String,
    var actor: String,
    createdBy: String,
    lastModifiedBy: String
) : Item(name, price, stockQuantity, createdBy, lastModifiedBy) {
    override fun change(item: Item, requestor: String) {
        if (item is Movie) {
            this.director = item.director
            this.actor = item.actor
            super.change(item, requestor)
        } else {
            typeMismatch("입력된 상품 정보가 영화가 아닙니다.")
        }
    }

    override fun toString(): String {
        return "Movie(${super.toString()}, director='$director', actor='$actor')"
    }

    companion object {
        fun from(request: RegistItemRequest) = Movie(
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity,
            director = request.additionalProperties["director"] ?: "",
            actor = request.additionalProperties["actor"] ?: "",
            createdBy = request.createdBy,
            lastModifiedBy = request.createdBy
        )
    }
}