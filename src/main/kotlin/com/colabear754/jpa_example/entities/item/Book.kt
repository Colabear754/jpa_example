package com.colabear754.jpa_example.entities.item

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
) : BaseItem(name, price, stockQuantity, createdBy, lastModifiedBy)