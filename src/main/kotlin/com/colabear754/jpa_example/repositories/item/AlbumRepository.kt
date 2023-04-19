package com.colabear754.jpa_example.repositories.item

import com.colabear754.jpa_example.domain.entities.item.Album
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AlbumRepository : JpaRepository<Album, UUID> {
    fun findByName(name: String): List<Album>
}