package com.colabear754.jpa_example.entities.item

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
    fun change(movie: Movie) {
        super.change(movie)
        this.director = movie.director
        this.actor = movie.actor
    }
}