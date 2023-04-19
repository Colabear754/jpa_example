package com.colabear754.jpa_example.repositories.item

import com.colabear754.jpa_example.domain.entities.item.Book
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BookRepository : JpaRepository<Book, UUID> {
    fun findByName(name: String): List<Book>
}
