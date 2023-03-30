package com.colabear754.jpa_example.repositories.item

import com.colabear754.jpa_example.entities.item.Book
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BookRepository : JpaRepository<Book, UUID> {
    fun findByName(name: String): List<Book>
}
