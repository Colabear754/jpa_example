package com.colabear754.jpa_example.entities.item

import com.colabear754.jpa_example.dto.member.item.RegistItemRequest
import com.colabear754.jpa_example.util.typeMismatch
import jakarta.persistence.Entity

@Entity
class Album(
    name: String,
    price: Int,
    stockQuantity: Int = 1,
    var artist: String,
    var etc: String,
    createdBy: String,
    lastModifiedBy: String
) : Item(name, price, stockQuantity, createdBy, lastModifiedBy) {
    override fun change(item: Item) {
        if (item is Album) {
            this.artist = item.artist
            this.etc = item.etc
            super.change(item)
        } else {
            typeMismatch("입력된 상품 정보가 앨범이 아닙니다.")
        }
    }

    override fun toString(): String {
        return "Album(${super.toString()}, artist='$artist', etc='$etc')"
    }

    companion object {
        fun from(request: RegistItemRequest) = Album(
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity,
            artist = request.additionalProperties["artist"] ?: "",
            etc = request.additionalProperties["etc"] ?: "",
            createdBy = request.createdBy,
            lastModifiedBy = request.createdBy
        )
    }
}