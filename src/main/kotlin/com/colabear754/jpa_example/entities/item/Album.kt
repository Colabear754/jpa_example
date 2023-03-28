package com.colabear754.jpa_example.entities.item

import jakarta.persistence.Entity

@Entity
class Album(
    name: String,
    price: Int,
    stockQuantity: Int = 1,
    createdBy: String,
    lastModifiedBy: String,
    var artist: String,
    var etc: String
) : BaseItem(name, price, stockQuantity, createdBy, lastModifiedBy)