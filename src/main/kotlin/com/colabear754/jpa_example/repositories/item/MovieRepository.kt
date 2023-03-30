package com.colabear754.jpa_example.repositories.item

import com.colabear754.jpa_example.entities.item.Movie
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MovieRepository : JpaRepository<Movie, UUID> {
    fun findByName(name: String): List<Movie>
}
