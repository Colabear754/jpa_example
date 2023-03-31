package com.colabear754.jpa_example.entities.item

import com.colabear754.jpa_example.util.typeMismatch
import jakarta.persistence.Entity

@Entity
class Movie(
    name: String,
    price: Int,
    stockQuantity: Int = 1,
    var director: String,
    var actor: String,
    createdBy: String,
    lastModifiedBy: String
) : Item(name, price, stockQuantity, createdBy, lastModifiedBy) {
    override fun change(item: Item) {
        if (item is Movie) {
            item.director = item.director
            item.actor = item.actor
            super.change(item)
        } else {
            typeMismatch("입력된 상품 정보가 영화가 아닙니다.")
        }
    }

    override fun toString(): String {
        return "Movie(${super.toString()}, director='$director', actor='$actor')"
    }
}