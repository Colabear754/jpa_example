package com.colabear754.jpa_example.entities.item

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
) : Item(name, price, stockQuantity, createdBy, lastModifiedBy)